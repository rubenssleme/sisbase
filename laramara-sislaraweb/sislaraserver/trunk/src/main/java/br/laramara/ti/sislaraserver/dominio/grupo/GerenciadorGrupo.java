package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Resultado;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.pendencia.AutomatizadorPendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaAtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.pendencia.TipoPendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.AutomatizadorStatusUsuario;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fachadas.FachadaSeguranca;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioModuloPeriodo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;

@Component
public class GerenciadorGrupo {
	
	private Logger logger;
	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private RepositorioPendencia repositorioPendencia;
	@Inject
	private RepositorioAtendimentoGrupo repositorioAtendimentoGrupo;
	@Inject
	private RepositorioModuloPeriodo repositorioModuloPeriodo;
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	@Inject
	private AutomatizadorStatusUsuario automatizadorStatusUsuario; 
	@Inject
	private AutomatizadorStatusGrupo automatizadorStatusGrupo;
	@Inject
	private AutomatizadorModuloPeriodo automatizadorModuloPeriodo;
	@Inject
	private AutomatizadorPendencia automatizadoPendencia;
	@Inject
	private GerenciadorReuniaoIntegracao gerenciadorReuniaoIntegracao;
	@Inject
	private FachadaSeguranca fachadaSeguranca;
	
	public GerenciadorGrupo(){
		this.logger = Logger.getLogger(getClass());
	}

	public synchronized ResultadoEdicaoModuloPeriodo editarModuloPeriodo(
			ModuloPeriodo moduloPeriodo, ContaAcesso contaAcesso) {
		ResultadoEdicaoModuloPeriodo resultado = new ResultadoEdicaoModuloPeriodo();
		
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());
		ModuloPeriodo moduloPeriodoReuniaoIntegracaoOriginal = grupo.obterModuloPeriodoDeReuniaoDeIntegracao();

		moduloPeriodo.vincularContaAcessoResponsavelPorOperacao(contaAcesso);
		moduloPeriodo.validarAutorizacaoAlteracaoDeGruposDeReunisaoDeIntegracao(contaAcesso,
				moduloPeriodoReuniaoIntegracaoOriginal);
		moduloPeriodo.validarExistenciaDeIntegradosNoOSEeCL(grupo);
		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (moduloPeriodo.validado()){
			resultado = alterarModuloPeriodo(contaAcesso, moduloPeriodo);
		}else{
			resultado.adicionarErro(moduloPeriodo
						.obterDescricaoErros());
		}
		return resultado;
	}
	
	public synchronized ResultadoEdicaoAtendimentoGrupo cancelarAtendimentoGrupo(
			AtendimentoGrupo atendimentoGrupoACancelar, ContaAcesso contaAcesso) {
		ResultadoEdicaoAtendimentoGrupo resultadoEdicaoAtendimentoGrupo = new ResultadoEdicaoAtendimentoGrupo();
		atendimentoGrupoACancelar.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		if (atendimentoGrupoACancelar.validado()){
			logger.info("Solicitação de Cancelamento de Atendimento no " + atendimentoGrupoACancelar
					+ " solicitado pela " + contaAcesso);
			atendimentoGrupoACancelar.cancelar();
			AtendimentoGrupo atendimentoGrupoCancelado = repositorioAtendimentoGrupo.salvar(atendimentoGrupoACancelar);
			resultadoEdicaoAtendimentoGrupo.efetuadoComSucesso(atendimentoGrupoCancelado);
			aplicarRegrasAposEdicaoDeAtendimentoDeGrupo(resultadoEdicaoAtendimentoGrupo, atendimentoGrupoACancelar,
					contaAcesso);
		}else{
			resultadoEdicaoAtendimentoGrupo.setMensagem(atendimentoGrupoACancelar.obterDescricaoErros());
		}
		return resultadoEdicaoAtendimentoGrupo;
	}

	private List<Usuario> obterUsuariosComStatusProvisorioAcessoOuIntegracoEmGrupoAtivoDistintosComDescricaoDoSolicitanteModuloAEEIgnorandoExcecao(
			ModuloPeriodo moduloPeriodoSolicitante) {
		List<Usuario> usuariosComStatusProvisorioAcessoOuIntegradoEmGrupoAtivoDistintosComDescricaoDoSolicitanteModuloAEE = new ArrayList<>();
		Grupo grupoSolicitante = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoSolicitante.getId());
		if (!grupoSolicitante.eDescricaoPsicossocialDeJovensEAdultos()
				&& !grupoSolicitante.eDescricaoAEAtividadesDeVidaAutonoma()) {
			List<Grupo> gruposAtivosComDescricaoDoSolicitanteEModuloAEE = repositorioGrupo
					.obterGruposAtivosComDescricaoEModulo(
							grupoSolicitante.getDescricaoTipoAtendimento(),
							Modulo.fabricaModuloAtendimentoEspecificoEspecializado());
	
			for (Usuario usuarioComStatusProvisorioAcessoOuIntegrado : moduloPeriodoSolicitante
					.obterUsuariosComStatusIntegradoOuProvisorioOuAcesso()) {
				if (gruposAtivosComDescricaoDoSolicitanteEModuloAEE.stream()
						.anyMatch(grupo -> grupo.possuiUsuariosIntegradoOuProvisorioOuAcessoNoModuloPeriodoAEE(
								usuarioComStatusProvisorioAcessoOuIntegrado, grupoSolicitante, moduloPeriodoSolicitante))) {
					usuariosComStatusProvisorioAcessoOuIntegradoEmGrupoAtivoDistintosComDescricaoDoSolicitanteModuloAEE
							.add(usuarioComStatusProvisorioAcessoOuIntegrado);
				}
			}
		}
		return usuariosComStatusProvisorioAcessoOuIntegradoEmGrupoAtivoDistintosComDescricaoDoSolicitanteModuloAEE;
	}

	public synchronized ResultadoEdicaoAtendimentoGrupo editarAtendimentoGrupo(
			ModuloPeriodo moduloPeriodo, AtendimentoGrupo atendimentoGrupoASalvar, ContaAcesso contaAcesso) {
		ResultadoEdicaoAtendimentoGrupo resultadoEdicaoAtendimentoGrupo = new ResultadoEdicaoAtendimentoGrupo();
		atendimentoGrupoASalvar.vincularContaAcessoResponsavelPorOperacao(contaAcesso);
		atendimentoGrupoASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		if (atendimentoGrupoASalvar.validado()) {
			if (!possuiPendenciasAnterioresNaoResolvidasDoGrupo(moduloPeriodo, atendimentoGrupoASalvar, contaAcesso)) {
				AtendimentoGrupo atendimentoGrupoSalvo = null;
				logger.info("Edição do " + atendimentoGrupoASalvar + " solicitado pela " + contaAcesso);
				ModuloPeriodo moduloPeriodoASalvar = repositorioGrupo.obterModuloPeriodoPorId(moduloPeriodo.getId());
				moduloPeriodoASalvar.adicionarNovoAtendimentoGrupo(atendimentoGrupoASalvar);
				moduloPeriodoASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
				if (moduloPeriodoASalvar.validado()) {
					if (atendimentoGrupoASalvar.possuiId()) {
						atendimentoGrupoSalvo = repositorioAtendimentoGrupo.salvar(atendimentoGrupoASalvar);
					} else {
						atendimentoGrupoSalvo = repositorioModuloPeriodo.salvar(moduloPeriodoASalvar)
								.obterAtendimentoGrupoCriadoMaisRecentemente();
					}
					resultadoEdicaoAtendimentoGrupo.efetuadoComSucesso(atendimentoGrupoSalvo);
				} else {
					resultadoEdicaoAtendimentoGrupo.adicionarErro(moduloPeriodoASalvar.obterDescricaoErros());
				}
				logger.info("Edição do " + atendimentoGrupoSalvo + " efetuada com sucesso.");
				aplicarRegrasAposEdicaoDeAtendimentoDeGrupo(resultadoEdicaoAtendimentoGrupo, atendimentoGrupoSalvo,
						contaAcesso);
			} else {
				resultadoEdicaoAtendimentoGrupo
						.adicionarErro("Existe pendência de atendimento de grupo anterior não resolvida.");
			}
		} else {
			resultadoEdicaoAtendimentoGrupo.adicionarErro(atendimentoGrupoASalvar.obterDescricaoErros());
		}
		return resultadoEdicaoAtendimentoGrupo;
	}
	
	private boolean possuiPendenciasAnterioresNaoResolvidasDoGrupo(ModuloPeriodo moduloPeriodo,
			AtendimentoGrupo atendimentoGrupo, ContaAcesso contaAcesso) {
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());

		List<Pendencia> pendenciasDeGrupoDoProfissional = repositorioPendencia
				.obterTodasNaoResolvidasDeGrupoComDataPassada(Arrays.asList(TipoPendencia.ATENDIMENTO_GRUPO),
						moduloPeriodo, contaAcesso.getProfissional());

		List<Pendencia> pendenciasDeAtendimentoDeGrupoAnterioresNaoResolvidas = pendenciasDeGrupoDoProfissional.stream()
				.filter(pendencia -> ((PendenciaAtendimentoGrupo)pendencia).possuiDataAnterior(atendimentoGrupo.obterData())
						&& ((PendenciaAtendimentoGrupo)pendencia).getGrupo().equals(grupo))
				.collect(Collectors.toList());
		return !pendenciasDeAtendimentoDeGrupoAnterioresNaoResolvidas.isEmpty();
	}

	public synchronized void aplicarRegrasAposEdicaoDeAtendimentoDeGrupo(
			ResultadoEdicaoAtendimentoGrupo resultadoEdicaoAtendimentoGrupo, AtendimentoGrupo atendimentoGrupo,
			ContaAcesso contaAcesso) {
		if (resultadoEdicaoAtendimentoGrupo.sucesso()){
			Resultado resultadoExcessoFaltas = automatizadorEspera
					.processarAtendimentoGrupoInclusaoNaListaDeEsperaPorExecessoDeFaltas(
							atendimentoGrupo,
							contaAcesso);
			automatizadoPendencia.transferirPendenciaDeReuniaoDeIntegracaoSeNecessario(atendimentoGrupo);
			automatizadoPendencia.atualizarPendenciasAtendimentoGrupo(atendimentoGrupo.obterProfissionais());
			ModuloPeriodo moduloPeriodo = repositorioModuloPeriodo
					.obterPorAtendimentoGrupo(atendimentoGrupo);
			atualizarStatusDeUsuarioEmGruposAEEAtivosDeProvisorioParaAcessoAposFrequenciaATEmReuniaoDeIntegracao(
					moduloPeriodo, atendimentoGrupo, contaAcesso);
			resultadoEdicaoAtendimentoGrupo.efetuadoComSucesso(
					atendimentoGrupo,
					resultadoExcessoFaltas.getMensagem());
			resultadoEdicaoAtendimentoGrupo
					.setMensagem(moduloPeriodo.obterRelacaoUsuariosComStatusProvisorioOuAcesso());
		}
	}
	
	private void atualizarStatusDeUsuarioEmGruposAEEAtivosDeProvisorioParaAcessoAposFrequenciaATEmReuniaoDeIntegracao(
			ModuloPeriodo moduloPeriodoSolicitante, AtendimentoGrupo atendimentoGrupoSolicitante, ContaAcesso contaAcesso) {
		if (atendimentoGrupoSolicitante.estaNormal() && moduloPeriodoSolicitante.ePossuiModuloDeReuniaoIntegracao()) {
			for (Usuario usuarioComFrequenciaAT : atendimentoGrupoSolicitante
					.obterUsuariosComAtendimentoDeGrupoNormalEFrequenciaAT()) {
				List<ModuloPeriodo> moduloPeriodosDeGruposAtivos = repositorioGrupo
						.obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(usuarioComFrequenciaAT);
				for (ModuloPeriodo moduloPeriodoAEEEmGrupoAtivo : moduloPeriodosDeGruposAtivos.stream()
						.filter(moduloPeriodo -> moduloPeriodo.eAtendimentoEspecificoEspecializado())
						.collect(Collectors.toList())) {
					moduloPeriodoAEEEmGrupoAtivo.atualizarStatusDeProvisorioParaAcesso(usuarioComFrequenciaAT,
							repositorioGrupo.obterGrupoPorIdAtendimentoGrupo(atendimentoGrupoSolicitante.getId()),
							atendimentoGrupoSolicitante.getData());
					if (moduloPeriodoAEEEmGrupoAtivo.sofreuMudancaDeStatusDeProvisorioParaAcesso()) {
						editarModuloPeriodo(moduloPeriodoAEEEmGrupoAtivo, ContaAcesso.obterAcessoRoot());
					}
				}
			}
		}
	}

	public synchronized ResultadoGeracaoAtendimento gerarAtendimentos(Pendencia pendencia,
			TokenDTO tokenDto) {
		ResultadoGeracaoAtendimento resultadoGeracaoAtendimento = new ResultadoGeracaoAtendimento();
		
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(((PendenciaAtendimentoGrupo)pendencia).getModuloPeriodo().getId());
		String nomeDoGrupoETurma = grupo.obterNomeGrupoETurma();
		if (!pendencia.estaResolvida()) {
			if (!fachadaSeguranca.estaBloqueadoParaEdicao(nomeDoGrupoETurma, tokenDto)) {
				EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento = new EspecificacaoGeracaoAtendimento(
						((PendenciaAtendimentoGrupo)pendencia).getModuloPeriodo().getId(), ((PendenciaAtendimentoGrupo)pendencia).obterData(), pendencia.getHorario());
				resultadoGeracaoAtendimento = gerarAtendimentos(especificacaoGeracaoAtendimento, tokenDto);
			} else {
				resultadoGeracaoAtendimento
						.adicionarErro(fachadaSeguranca.obterContaAcessoEditando(nomeDoGrupoETurma).obterMensagens());
			}
		} else {
			resultadoGeracaoAtendimento.adicionarErro("A pendencia já foi resolvida.");
		}
		return resultadoGeracaoAtendimento;
	}

	public synchronized ResultadoGeracaoAtendimento gerarAtendimentos(
			EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento,
			TokenDTO tokenDto) {
		ResultadoGeracaoAtendimento resultado = new ResultadoGeracaoAtendimento();

		Grupo grupo = repositorioGrupo
				.obterGrupoPorIdModuloPeriodo(especificacaoGeracaoAtendimento.getIdModuloPeriodo());
		ModuloPeriodo moduloPeriodo = repositorioGrupo
				.obterModuloPeriodoPorId(especificacaoGeracaoAtendimento.getIdModuloPeriodo());

		moduloPeriodo.gerarAtendimentos(grupo, especificacaoGeracaoAtendimento);
		if (moduloPeriodo.validado()){
			resultado.efetuadoComSucesso(grupo, moduloPeriodo, moduloPeriodo.obterModeloAtendimentoGrupoCriadoMaisRecentemente());
		}else{
			resultado.adicionarErro(moduloPeriodo.obterDescricaoErros());
		}
		return resultado;
	}


	public ResultadoEdicaoModuloPeriodo alterarModuloPeriodo(ContaAcesso contaAcesso,
			ModuloPeriodo moduloPeriodoSolicitante) {
		ResultadoEdicaoModuloPeriodo resultadoEdicaoModuloPeriodo = new ResultadoEdicaoModuloPeriodo();
		Grupo grupoSolicitante = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoSolicitante.getId());
		List<Usuario> usuariosComStatusIntegradoProvisorioOuAcessoEmGrupoAtivoDistintoComDescricaoDoSolicitanteModuloAEE = obterUsuariosComStatusProvisorioAcessoOuIntegracoEmGrupoAtivoDistintosComDescricaoDoSolicitanteModuloAEEIgnorandoExcecao(
				moduloPeriodoSolicitante);
		if (usuariosComStatusIntegradoProvisorioOuAcessoEmGrupoAtivoDistintoComDescricaoDoSolicitanteModuloAEE.isEmpty()){
			List<Usuario> usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao = gerenciadorReuniaoIntegracao
					.obterUsuariosIntegradosEmGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao(
							moduloPeriodoSolicitante);
			List<Usuario> usuariosIntegradosEmReuniaoDeIntegracaoSemAtendimentoComFrequenciaAT = gerenciadorReuniaoIntegracao
					.obterUsuariosIntegradosEmReuniaoDeIntegracaoSemAtendimentoComFrequenciaAT(
							moduloPeriodoSolicitante);
			if (usuariosIntegradosEmReuniaoDeIntegracaoSemAtendimentoComFrequenciaAT.isEmpty()) {
				if (usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao
						.isEmpty()) {
					ModuloPeriodo moduloPeriodoSalvo = repositorioModuloPeriodo.salvar(moduloPeriodoSolicitante);
					automatizadorModuloPeriodo
							.aplicarRegraPropagacaoStatusNaoAcessoOuProvisorioAosOutrosModulosDoGrupo(
									moduloPeriodoSalvo);
					automatizadorModuloPeriodo
							.aplicarRegraDeIntegradoOuProvisorioOuAcessoAosModulosPeriodosDePssicossocialNosGruposDeAtendimentoEspecializadoGlobalOuTranstornoGlobalDoDesenvolvimento(
									moduloPeriodoSalvo);
					automatizadorStatusGrupo
							.efetuarDesligamentosEmTodosOsGruposAtivosDosUsuariosDesligados(moduloPeriodoSalvo);
					automatizadoPendencia
							.cancelarPendenciasPorExcessoDeFaltasDosUsuariosDesligados(moduloPeriodoSalvo);
					automatizadorStatusUsuario.atualizarStatusDoUsuario(moduloPeriodoSalvo);
					gerenciadorReuniaoIntegracao.incluirEmReuniaoDeIntegracaoSeNecessario(moduloPeriodoSalvo);
					resultadoEdicaoModuloPeriodo.efetuadoComSucesso(repositorioGrupo.obterModuloPeriodoPorId(moduloPeriodoSalvo.getId()));
					logger.info(contaAcesso + " editou os integrantes do " + moduloPeriodoSalvo);
				} else {
					resultadoEdicaoModuloPeriodo.adicionarErro("Usuários "
							+ TextoUtils.removerChaves(Usuario.obterNomesDosUsuarios(
									usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao))
							+ " já estão integrados em um grupo de reunião de integração.");
				}
			}else{
				resultadoEdicaoModuloPeriodo.adicionarErro("Usuários "
						+ TextoUtils.removerChaves(Usuario.obterNomesDosUsuarios(
								usuariosIntegradosEmReuniaoDeIntegracaoSemAtendimentoComFrequenciaAT))
						+ " possuem pendência de atendimento de RI a lançar ou estão com frequência diferente de AT na RI mais recente.");
			}
		}else{
			resultadoEdicaoModuloPeriodo.adicionarErro("Usuário(s) "
					+ TextoUtils.removerChaves(Usuario.obterNomesDosUsuarios(
							usuariosComStatusIntegradoProvisorioOuAcessoEmGrupoAtivoDistintoComDescricaoDoSolicitanteModuloAEE))
					+ " já estão com status PROVISORIO, ACESSO ou INTEGRADO em outro grupo ativo de "
					+ grupoSolicitante.getDescricaoTipoAtendimento().getNome() + ", módulo AEE.");
		}
		return resultadoEdicaoModuloPeriodo;
	}
}
