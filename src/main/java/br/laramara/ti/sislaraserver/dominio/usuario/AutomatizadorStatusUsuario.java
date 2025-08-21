package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.GravadorTexto;

@Component
public class AutomatizadorStatusUsuario {
	
	private final Logger logger = Logger.getLogger(AutomatizadorStatusUsuario.class);
	
	@Inject
	private RepositorioSislara repositorioSislara;
	@Inject
	private RepositorioUsuario repositorioUsuario;
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	

	public void atualizarStatusDoUsuario(ModuloPeriodo moduloPeriodoSalvo) {
		try {
			for (ModuloUsuario moduloUsuario : moduloPeriodoSalvo
					.getModulosUsuarios()) {
				for (StatusRelacaoComModulo statusRelacaoComModuloOrdenadoPorImportancia : StatusRelacaoComModulo
						.statusOrdenadoPorImportancia()) {
					List<Map<String, Object>> statusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDeReuniaoDeIntegracao = repositorioSislara
							.obterStatusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDoReuniaoDeIntegracao(
									moduloUsuario.getUsuario());
					if (existeOcorrenciaOuEquivalenciaDeStatus(
							statusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDeReuniaoDeIntegracao,
							statusRelacaoComModuloOrdenadoPorImportancia)) {
						Usuario usuarioAAlterar = repositorioUsuario
								.obterPorId(moduloUsuario.getUsuario().getId());
						if (!usuarioAAlterar.possuiStatusUsuario()
								|| !usuarioAAlterar
										.getStatusUsuarioAtual()
										.equals(statusRelacaoComModuloOrdenadoPorImportancia)) {
							usuarioAAlterar
									.adicionarStatusUsuario(statusRelacaoComModuloOrdenadoPorImportancia);
							repositorioUsuario.salvar(usuarioAAlterar);
							GravadorTexto.gravarHistoricoSeNecessario(true, usuarioAAlterar,
									statusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDeReuniaoDeIntegracao,
									Configuracao.DIRETORIO_PRONTUARIOS_ATUALIZACAO_STATUS);
							logger.info("Atualização automática de Status do Usuário efetuada com sucesso.");
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.fatal("Erro durante atualização automatica de status do Usuário. \nDetalhes: "
					+ e);
		}
	}
	
	private boolean existeOcorrenciaOuEquivalenciaDeStatus(
			List<Map<String, Object>> statusNosGrupos, StatusRelacaoComModulo status) {
		for (Map<String, Object> statusNoGrupo : statusNosGrupos) {
			StatusRelacaoComModulo statusNoGrupoAVerificar = StatusRelacaoComModulo
					.valueOf((String) statusNoGrupo.get("status"));
			if (status.equals(statusNoGrupoAVerificar
					.obterExatoOuEquivalente())) {
				return true;
			}
		}
		return false;
	}

	public synchronized void atualizarStatusDeUsuariosParaDesistenteOuRetorno() {
		try {
			for (Usuario usuario : repositorioUsuario.obterTodos()) {
				usuario.atualizarStatusUsuarioParaDesistenteOuRetorno();
				if (usuario.eDesistentePorExpiracaoDeRetorno()) {
					automatizadorEspera
							.cancelarTodasEsperasAguardandoDevidoExpiracaoDoRetorno(usuario);
				}
				if (usuario.possuiStatusUsuarioNovoASalvar()) {
					logger.info("Processo de atualização de status do Usuário será efetuado.");
					repositorioUsuario.salvar(usuario);
				}
			}
		} catch (Exception e) {
			logger.fatal("Erro durante atualização de status dos Usuários. \nDetalhes: "
					+ e);
		}
	}

	public void atualizarStatusParaRetornoSeNecessario(AtendimentoIndividual atendimentoIndividual) {
		if (atendimentoIndividual.possuiUsuario() && atendimentoIndividual.estaComFrequenciaAT()) {
			Usuario usuario = repositorioUsuario.obterPorId(atendimentoIndividual.getUsuario().getId());
			String mensagem = "Atualização automática de status para RETORNO devido atendimento individual de ";
			if (usuario.eCasoNovoOuNaoPossuiStatus()) {
				if (atendimentoIndividual.eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado()) {
					aplicarStatusRetorno(usuario,
							mensagem + "Avaliação Funcional - Atendimento Especifico Especializado. ");
					return;
				}
				if (atendimentoIndividual.eDescricaoServicoSocialModuloAvaliacaoETriagem()
						&& atendimentoIndividual.eProceja()) {
					aplicarStatusRetorno(usuario, mensagem + "Serviço Social - Avaliação e Triagem do PROCEJA. ");
					return;
				}
			}
			if (atendimentoIndividual.possuiUsuario() && usuario.ePossuiMenosDe21AnosEEstaDesligadoHaMaisDeUmAno()
					&& atendimentoIndividual.eDescricaoServicoSocialModuloAvaliacaoETriagem()
					&& atendimentoIndividual.eRetorno()
					&& atendimentoIndividual.atendimentoRealizadoUmAnoAposDesligamento(usuario)) {
				aplicarStatusRetorno(usuario,
						mensagem + "Serviço Social de usuário com menos de 21 anos e DESLIGADO há mais de 1 ano.");
				return;
			}
		}
	}
	
	private void aplicarStatusRetorno(Usuario usuario, String mensagem){
		usuario.atualizarStatusUsuarioParaRetorno();
		repositorioUsuario.salvar(usuario);
		GravadorTexto.gravarHistoricoSeNecessario(true, usuario, mensagem,
				Configuracao.DIRETORIO_PRONTUARIOS_ATUALIZACAO_STATUS);
		logger.info(mensagem + usuario);
	}
}
