package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.Automatizador;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.GerenciadorReuniaoIntegracao;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioModuloPeriodo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;

@Component
public class AutomatizadorPendencia extends Automatizador {
	
	private final Logger logger = Logger.getLogger(Automatizador.class);

	@Inject
	private RepositorioPendencia repositorioPendencia;
	
	@Inject
	private RepositorioEspera repositorioEspera;
	
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;

	@Inject
	private RepositorioAtendimentoIndividual repositorioAtendimentoIndividual;
	
	@Inject
	private RepositorioGrupo repositorioGrupo;
	
	@Inject
	private GerenciadorReuniaoIntegracao gerenciadorReuniaoIntegracao;
	
	@Inject
	private RepositorioUsuario repositorioUsuario;
	
	@Inject
	private RepositorioModuloPeriodo repositorioModuloPeriodo;
	
	public synchronized void atualizarPendencias() {
		atualizarPendenciasPorExcessoDeFaltas();
		atualizarPendenciasDeInclusaoDeReuniaoDeIntegracao();
	}

	private void atualizarPendenciasDeInclusaoDeReuniaoDeIntegracao() {
		List<Pendencia> pendenciasInclusaoDeReuniaoIntegracao = repositorioPendencia
				.obterTodasNaoResolvidas(Arrays.asList(TipoPendencia.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO));
		for (Pendencia pendencia : pendenciasInclusaoDeReuniaoIntegracao) {
			ModuloPeriodo moduloPeriodoSolicitante = pendencia.getModuloPeriodo();
			ModuloPeriodo modulosPeriodosReuniaoIntegracaoComDataFuturaEPeriodoCompativelCandidatos = repositorioGrupo
					.obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComDataFuturaEPeriodoCompativel(
							moduloPeriodoSolicitante)
					.stream().findFirst().orElse(null);
			if (modulosPeriodosReuniaoIntegracaoComDataFuturaEPeriodoCompativelCandidatos != null){
				Grupo grupoSolicitante = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoSolicitante.getId());
				modulosPeriodosReuniaoIntegracaoComDataFuturaEPeriodoCompativelCandidatos.adicionarUsuario(
						repositorioUsuario.obterPorId(pendencia.getProntuario()),
						"---------------------------------------------------------------"
						+ "\nInclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração. "
						+ "\nData da inclusão: " + DataHoraUtils.formatarDataHora(MaquinaTempo.obterInstancia().obterCalendarioAtual())
						+ "\nSolicitado pelo Grupo: " + grupoSolicitante.getNomeGrupo().getNome() + "-" + grupoSolicitante.getTurma()
						+ "\nMódulo: " + moduloPeriodoSolicitante.getModulo().getNome()
						+ "\nPeriodo referencia: " + moduloPeriodoSolicitante.periodoHorario()
						+ "\n-------------------------------------------------------------", false);
				if (modulosPeriodosReuniaoIntegracaoComDataFuturaEPeriodoCompativelCandidatos.possuiUsuarioNovo()) {
					repositorioModuloPeriodo.salvar(modulosPeriodosReuniaoIntegracaoComDataFuturaEPeriodoCompativelCandidatos);
					pendencia.marcarComoResolvida();
					repositorioPendencia.salvar(pendencia);
					logger.info("Pendência de inclusão de reunião de integração resolvida com sucesso.");
				}
			}
		}
	}

	public synchronized void atualizarPendenciasDeTransferenciasDeReuniaoIntegracao() {
		List<Pendencia> pendenciasReuniaoIntegracao = repositorioPendencia
				.obterTodasNaoResolvidas(Arrays.asList(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO));
		for (Pendencia pendenciaReuniaoIntegracao : pendenciasReuniaoIntegracao) {
			ModuloPeriodo moduloPeriodoReuniaoIntegracaoReferencia = pendenciaReuniaoIntegracao.getModuloPeriodo();
			ModuloPeriodo moduloPeriodoCompativel = gerenciadorReuniaoIntegracao
					.obterModuloPeriodoReuniaoIntegracaoComDataFuturaEPeriodoCompativel(
							moduloPeriodoReuniaoIntegracaoReferencia);
			Usuario usuario = repositorioUsuario.obterPorId(pendenciaReuniaoIntegracao.getProntuario());
			ModuloUsuario moduloUsuarioReniaoIntegracaoReferencia = moduloPeriodoReuniaoIntegracaoReferencia
					.obterModuloUsuario(usuario);
			if (moduloPeriodoCompativel != null && moduloUsuarioReniaoIntegracaoReferencia != null) {
				moduloPeriodoCompativel.adicionarUsuario(usuario,
						moduloUsuarioReniaoIntegracaoReferencia.getObs()
								+ "---------------------------------------------------------------"
								+ "\nTransferência automática de Reunião de Integração devido falta no grupo original. Data da transferência: "
								+ DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual())
								+ "\n-------------------------------------------------------------",
						moduloUsuarioReniaoIntegracaoReferencia.isAprovado());
				if (moduloPeriodoCompativel.possuiUsuarioNovo()) {
					pendenciaReuniaoIntegracao.marcarComoResolvida();
					moduloPeriodoReuniaoIntegracaoReferencia.removerAutomaticamenteParaOutroGrupoDeReuniaoIntegracao(usuario);
					repositorioModuloPeriodo.salvar(moduloPeriodoReuniaoIntegracaoReferencia);
					repositorioModuloPeriodo.salvar(moduloPeriodoCompativel);
					repositorioPendencia.salvar(pendenciaReuniaoIntegracao);
					logger.info("Pendência de transferência de reunião de integração resolvida com sucesso.");
				}
			}
		}
	}

	public void atualizarPendenciasAtendimentoGrupo(Profissional profissional) {
		List<Pendencia> pendenciasAtendimentoGrupo = repositorioPendencia
				.obterTodasNaoResolvidas(Arrays.asList(TipoPendencia.ATENDIMENTO_GRUPO), profissional);

		List<Pendencia> pendenciasAtendimentoGrupoResolvidas = pendenciasAtendimentoGrupo.stream()
				.filter(pendenciaAtendimentoGrupo -> pendenciaAtendimentoGrupo.posterior()
						&& possuiAtendimentoGrupoComFrequenciaATouFRouFPouOAouRCnoProfissionaleStatusDeAtendimentoNormal(pendenciaAtendimentoGrupo))
				.collect(Collectors.toList());
		marcarPendenciasComoResolvida(pendenciasAtendimentoGrupoResolvidas);
		salvarPendencias(pendenciasAtendimentoGrupoResolvidas);
	}

	private void salvarPendencias(List<Pendencia> pendenciasAtendimentoGrupoResolvidas) {
		pendenciasAtendimentoGrupoResolvidas.forEach(
				pendenciaAtendimentoGrupoResolvida -> repositorioPendencia.salvar(pendenciaAtendimentoGrupoResolvida));
	}

	private void marcarPendenciasComoResolvida(List<Pendencia> pendenciasResolvidas) {
		pendenciasResolvidas.forEach(pendenciaResolvida -> pendenciaResolvida.marcarComoResolvida());
	}

	private boolean possuiAtendimentoGrupoComFrequenciaATouFRouFPouOAouRCnoProfissionaleStatusDeAtendimentoNormal(Pendencia pendenciaAtendimentoGrupo) {
		return pendenciaAtendimentoGrupo.getModuloPeriodo().getAtendimentosGrupo().stream()
				.anyMatch(atendimentoGrupo -> atendimentoGrupo.obterData().equals(pendenciaAtendimentoGrupo.getData())
						&& atendimentoGrupo.possuiProfissionaisComFrequenciaATouFRouFPouOAouRCeStatusDeAtendimentoNormal());
	}

	public synchronized void atualizarPendenciasAtendimentoIndividual(Profissional profissional) {
		atualizarPendenciaAposAtendimentoIndividualEfetuado(profissional);
		atualizarPendenciaAposMudancaDeStatusDoAgendamento(profissional);
	}

	private void atualizarPendenciaAposMudancaDeStatusDoAgendamento(Profissional profissional) {
		List<Pendencia> pendenciasAtendimentoIndividual = obterPendenciasNaoResovidasDeAtendimentoIndividual(profissional);
		
		List<Pendencia> pendenciasDeAtendimentoIndividualComAgendamentoGeradorNoStatusNaoAgendado = pendenciasAtendimentoIndividual
				.stream().filter(pendencia -> !pendencia.getAgendamento().estaAgendado()).collect(Collectors.toList());
		marcarPendenciasComoResolvida(pendenciasDeAtendimentoIndividualComAgendamentoGeradorNoStatusNaoAgendado);
		salvarPendencias(pendenciasDeAtendimentoIndividualComAgendamentoGeradorNoStatusNaoAgendado);
	}

	private List<Pendencia> obterPendenciasNaoResovidasDeAtendimentoIndividual(Profissional profissional) {
		return repositorioPendencia.obterTodasNaoResolvidas(Arrays.asList(TipoPendencia.ATENDIMENTO_INDIVIDUAL_USUARIO,
				TipoPendencia.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO), profissional);
	}

	private void atualizarPendenciaAposAtendimentoIndividualEfetuado(Profissional profissional) {
		List<Pendencia> pendenciasAtendimentoIndividual = obterPendenciasNaoResovidasDeAtendimentoIndividual(profissional);

		List<Pendencia> pendenciasAtendimentoIndividualResolvidas = pendenciasAtendimentoIndividual.stream()
				.filter(pendenciaAtendimentoIndividual -> possuiAtendimentoIndividualComFrequenciaATouFPnoProfissional(
						pendenciaAtendimentoIndividual))
				.collect(Collectors.toList());
		marcarPendenciasComoResolvida(pendenciasAtendimentoIndividualResolvidas);
		salvarPendencias(pendenciasAtendimentoIndividualResolvidas);
	}

	private boolean possuiAtendimentoIndividualComFrequenciaATouFPnoProfissional(Pendencia pendenciaAtendimentoIndividual) {
		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
		if (pendenciaAtendimentoIndividual.eAtendimentoIndividualUsuario()) {
			especificacaoPesquisaAtendimentoIndividual
					.setProntuario(pendenciaAtendimentoIndividual.getAgendamento().getUsuario().getId().toString());
		} else if (pendenciaAtendimentoIndividual.eAtendimentoIndividualPreCadastro()) {
			if (pendenciaAtendimentoIndividual.possuiUsuarioCriadoPeloPreCadastro()){
				especificacaoPesquisaAtendimentoIndividual.setProntuario(
						pendenciaAtendimentoIndividual.obterUsuarioCriadoPeloPreCadastro().getId().toString());
			}else{
				especificacaoPesquisaAtendimentoIndividual
						.setPreCadastro(pendenciaAtendimentoIndividual.getAgendamento().getPreCadastro());
			}
		}
		especificacaoPesquisaAtendimentoIndividual.setDescricaoTipoAtendimento(
				pendenciaAtendimentoIndividual.getAgendamento().getDescricaoTipoAtendimento());
		especificacaoPesquisaAtendimentoIndividual
				.setModulo(pendenciaAtendimentoIndividual.getAgendamento().getModulo());
		especificacaoPesquisaAtendimentoIndividual
				.setDataInicio(pendenciaAtendimentoIndividual.getAgendamento().getData());
		List<AtendimentoIndividual> atendimentosIndividuais = repositorioAtendimentoIndividual
				.obterPor(especificacaoPesquisaAtendimentoIndividual);
		atendimentosIndividuais
				.addAll(obterAtendimentosDeModuloAcompanhamentoNoCasoDePendenciaComDescricaoServicoSocialOuPsicologiaEModuloAvaliacaoETriagem(
						pendenciaAtendimentoIndividual, especificacaoPesquisaAtendimentoIndividual));
		atendimentosIndividuais
				.addAll(obterAtendimentosDeDescricaoAcompanhamentoDeServicoDeAtencaoEspecializadaEmOrtopticaModuloAtendimentoEspecificaoEspecializado(
						pendenciaAtendimentoIndividual, especificacaoPesquisaAtendimentoIndividual));
		boolean existeAtendimentoIndividualComFrequenciaATParaUsuarioEProfissional = atendimentosIndividuais.stream()
				.anyMatch(atendimentoIndividual -> atendimentoIndividual
						.possuiProfissionalComFrequenciaATouFP(pendenciaAtendimentoIndividual.getAgendamento().getProfissional()));
		return existeAtendimentoIndividualComFrequenciaATParaUsuarioEProfissional;
	}

	private Collection<? extends AtendimentoIndividual> obterAtendimentosDeDescricaoAcompanhamentoDeServicoDeAtencaoEspecializadaEmOrtopticaModuloAtendimentoEspecificaoEspecializado(
			Pendencia pendenciaAtendimentoIndividual,
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual) {
		List<AtendimentoIndividual> atendimentosIndividuals = new ArrayList<>();
		if (pendenciaAtendimentoIndividual.eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtopticaModuloAtendimentoEspecificoEspecializado()) {
			especificacaoPesquisaAtendimentoIndividual.setDescricaoTipoAtendimento(
					DescricaoTipoAtendimento.fabricarDescricaoAcompanhamentoServicoAtencaoEspecializadaOrtoptica());
			atendimentosIndividuals
					.addAll(repositorioAtendimentoIndividual.obterPor(especificacaoPesquisaAtendimentoIndividual));
		}
		return atendimentosIndividuals;
	}

	private List<AtendimentoIndividual> obterAtendimentosDeModuloAcompanhamentoNoCasoDePendenciaComDescricaoServicoSocialOuPsicologiaEModuloAvaliacaoETriagem(
			Pendencia pendenciaAtendimentoIndividual,
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual) {
		List<AtendimentoIndividual> atendimentosIndividuals = new ArrayList<>();
		if (pendenciaAtendimentoIndividual.eDescricaoServicoSocialModuloAvaliacaoETriagem()
				|| pendenciaAtendimentoIndividual.eDescricaoPsicologiaModuloAvaliacaoETriagem()) {
			especificacaoPesquisaAtendimentoIndividual.setModulo(Modulo.fabricaModuloAcompanhamento());
			atendimentosIndividuals
					.addAll(repositorioAtendimentoIndividual.obterPor(especificacaoPesquisaAtendimentoIndividual));
		}
		return atendimentosIndividuals;
	}

	private void atualizarPendenciasPorExcessoDeFaltas() {
		List<Pendencia> pendenciasPorExcessoDeFaltasNaoResolvidas = repositorioPendencia
				.obterTodasNaoResolvidas(Arrays.asList(TipoPendencia.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS));
		List<Pendencia> pendenciasPorExcessoDeFaltaResolvidas = pendenciasPorExcessoDeFaltasNaoResolvidas.stream()
				.filter(pendenciaNaoResolvida -> !possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(
						pendenciaNaoResolvida.getProntuario(), repositorioEspera, repositorioTipoAtendimento))
				.collect(Collectors.toList());
		
		marcarPendenciasComoResolvida(pendenciasPorExcessoDeFaltaResolvidas);
		salvarPendencias(pendenciasPorExcessoDeFaltaResolvidas);
	}

	public void transferirPendenciaDeReuniaoDeIntegracaoSeNecessario(AtendimentoGrupo atendimentoGrupo) {
		Grupo grupo = repositorioGrupo.obterGrupoPorIdAtendimentoGrupo(atendimentoGrupo.getId());
		if (grupo.eDescricaoServicoSocialComModuloReuniaoDeIntegracao()) {
			List<Usuario> usuariosComAtendimentoGrupoNormalEFrequenciaFUouFJ = atendimentoGrupo
					.obterUsuariosComAtendimentoDeGrupoNormalEFrequenciaFUouFJ();
			for (Usuario usuarioComFUouFJ : usuariosComAtendimentoGrupoNormalEFrequenciaFUouFJ) {
				if (!possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(usuarioComFUouFJ.getId(),
						repositorioEspera, repositorioTipoAtendimento)
						&& !repositorioPendencia
								.possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(usuarioComFUouFJ)
						&& !gerenciadorReuniaoIntegracao
								.existeUsuarioIntegradoEmGrupoAtivoDeReunicaoDeIntegracaoDistintoDoSolicitante(
										grupo.obterModuloPeriodoDeReuniaoDeIntegracao(), usuarioComFUouFJ)) {
					Pendencia pendencia = new Pendencia();
					pendencia.setTipoPendencia(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO);
					pendencia.setProntuario(usuarioComFUouFJ.getId());
					pendencia.setProfissionaisAfetados(
							Profissional.obterProfissionaisReponsaveisPeloServicoSocial());
					pendencia.setModuloPeriodo(grupo.obterModuloPeriodoDeReuniaoDeIntegracao());
					repositorioPendencia.salvar(pendencia);
				} 
				if (possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(usuarioComFUouFJ.getId(), repositorioEspera,
						repositorioTipoAtendimento)) {
					ModuloPeriodo moduloPeriodoReuniaoIntegracaoReferencia = grupo
							.obterModuloPeriodoDeReuniaoDeIntegracao();
					moduloPeriodoReuniaoIntegracaoReferencia.removerAutomaticamentePorExcessoDeFalta(usuarioComFUouFJ);
					repositorioModuloPeriodo.salvar(moduloPeriodoReuniaoIntegracaoReferencia);
					logger.info("Inclusao de pendência de transferência de prontuário para reunião de integração efetuada com sucesso.");
				}
			}
		}
	}

	public void cancelarPendenciasPorExcessoDeFaltasDosUsuariosDesligados(
			ModuloPeriodo moduloPeriodo) {
		for (Usuario usuario : moduloPeriodo.obterUsuariosComStatusDesligado()) {
			List<Pendencia> todasPendenciasNaoResolvidasDeExcessoDeFaltas = repositorioPendencia
					.obterTodasPendenciasDeExcessoDeFaltasNaoResolvidasPor(usuario);
			todasPendenciasNaoResolvidasDeExcessoDeFaltas.forEach((pendencia) -> {
				pendencia.marcarComoResolvida("Removido automaticamente pelo sistema após desligamento do usuário do grupo.");
				repositorioPendencia.salvar(pendencia);
			});
		}
	}
}
