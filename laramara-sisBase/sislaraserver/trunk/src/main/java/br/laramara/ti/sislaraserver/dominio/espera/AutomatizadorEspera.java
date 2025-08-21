package br.laramara.ti.sislaraserver.dominio.espera;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.Automatizador;
import br.laramara.ti.sislaraserver.dominio.Resultado;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaEnvioListaEsperaPorExcessoDeFaltas;
import br.laramara.ti.sislaraserver.dominio.seguranca.Area;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.GravadorTexto;

@Component
public class AutomatizadorEspera extends Automatizador {

	private final Logger logger = Logger.getLogger(AutomatizadorEspera.class);

	private Integer EXCESSO_FALTAS = 3;

	@Inject
	private RepositorioSislara repositorioSislara;
	@Inject
	private RepositorioEspera repositorioEspera;
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	@Inject
	private RepositorioPendencia repositorioPendencia;
	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private RepositorioAgendamento repositorioAgendamento;
	@Inject
	private RepositorioAtendimentoIndividual repositorioAtendimentoIndividual;

	private boolean processarInclusaoNaListaDeEsperaPorExecessoDeFaltasNaoJustificadas(
			Usuario usuario, ContaAcesso contaAcessoResponsavel) {
		boolean resultado = false;
		if (possuiExcessoFaltasNaoJustificadas(usuario)) {
			List<Profissional> profissionaisDosGruposAtivosComUsuarioIntegrado = repositorioGrupo
				.obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(usuario);
		
			resultado = salvarEsperaEIncluirPendenciaPorExcessoDeFaltas(usuario, profissionaisDosGruposAtivosComUsuarioIntegrado,
					"Incluído automaticamente por excesso de faltas não justificadas.", contaAcessoResponsavel);
		}
		return resultado;
	}
	
	private boolean salvarEsperaEIncluirPendenciaPorExcessoDeFaltas(Usuario usuario, List<Profissional> profissionaisSolicitantes,
			String obs, ContaAcesso contaAcessoResponsavel) {
		boolean resultado = false;
		
		Espera espera = new Espera();
		espera.setContaAcessoOperacao(contaAcessoResponsavel);
		espera.atribuirDataAtualNaDataExpectativa();
		espera.setDescricaoTipoAtendimento(repositorioTipoAtendimento.obterDescricaoTipoAtendimentoServicoSocial());
		espera.setModulo(repositorioTipoAtendimento.obterModuloExcessoDeFalta());
		espera.setSetor(Setor.NENHUM);
		espera.setUsuario(usuario);
		espera.setObs(obs);
		espera.setChaveAtendimentoAcarretouInclusao(obterChaveAtendimentosAcarretaramInclusao(usuario));
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (espera.validado() && !verificarSePossuiEsperaAguardandoOuJaExistiuEsperaComMesmaChaveDeAtendimento(espera, repositorioEspera)) {
			repositorioEspera.salvar(espera);
			salvarPendenciaEnvioDeEsperaServioSocialPorExcessoDeFaltas(espera, profissionaisSolicitantes);
			resultado = true;
			logger.info("Inclusão por excesso de falta não justificada da " + espera + " realizada com sucesso.");
		}
		return resultado;
	}

	private String obterChaveAtendimentosAcarretaramInclusao(Usuario usuario) {
		List<Map<String, Object>> obterFrequenciasAAvaliar = obterFrequenciasAAvaliar(usuario);

		String chavesAtendimentosAcarretaramInclusao = obterFrequenciasAAvaliar.stream().limit(EXCESSO_FALTAS)
				.map(p -> p.get("categoria") + "-" + p.get("data").toString())
				.collect(Collectors.joining("|"));
		return chavesAtendimentosAcarretaramInclusao;
	}


	public void retornarEsperaPorExcessoDeFaltasSeNecessario(AtendimentoIndividual atendimentoIndividual,
			ContaAcesso contaAcesso) {
		if (atendimentoIndividual.possuiUsuario()
				&& atendimentoIndividual.eDescricaoServicoSocialModuloAvaliacaoETriagem()
				&& atendimentoIndividual.estaComFrequenciaFUouFJ()
				&& !possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(atendimentoIndividual.getUsuario().getId(),
						repositorioEspera, repositorioTipoAtendimento)) {
			EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamentoDTO = new EspecificacaoPesquisaAgendamento();
			especificacaoPesquisaAgendamentoDTO.setDataInicio(atendimentoIndividual.getData());
			especificacaoPesquisaAgendamentoDTO.setDescricaoTipoAtendimento(
					repositorioTipoAtendimento.obterDescricaoTipoAtendimentoServicoSocial());
			especificacaoPesquisaAgendamentoDTO.setModulo(repositorioTipoAtendimento.obterAvaliacaoETriagem());
			especificacaoPesquisaAgendamentoDTO.setProntuario(atendimentoIndividual.getUsuario().getId().toString());

			List<Agendamento> agendamentos = repositorioAgendamento.obterPor(especificacaoPesquisaAgendamentoDTO);
			if (agendamentos.size() == 1) {
				Agendamento agendamento = agendamentos.get(0);
				if (agendamento.estaAgendadoOuReagendado() && agendamento.isOriginadoPorExcessoDeFaltas()) {
					salvarEsperaEIncluirPendenciaPorExcessoDeFaltas(atendimentoIndividual.getUsuario(),
							repositorioGrupo.obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(
									atendimentoIndividual.getUsuario()),
							"Incluído automaticamente em decorrência de falta no atendimento individual de avaliação e triagem agendado por causa de excesso de faltas.",
							contaAcesso);
				}
			}
		}
	}

	private void salvarPendenciaEnvioDeEsperaServioSocialPorExcessoDeFaltas(Espera espera, List<Profissional> profissionaisAfetados) {
		Pendencia pendencia = new PendenciaEnvioListaEsperaPorExcessoDeFaltas(espera.getUsuario().getId(),
				profissionaisAfetados);
		repositorioPendencia.salvar(pendencia);
	}
	
	private boolean possuiExcessoFaltasNaoJustificadas(Usuario usuario) {
		return possuiExcessoFaltasNaoJustificadasCTO(usuario);
	}

	private Long[] obterIdentificacoesDosModulosAtendimentoEspecificoEspecializadoEPsicossocialParaAsFamilias() {
		return new Long[] {
				Modulo.ID_MODULO_ATENDIMENTO_ESPECIFICO_ESPECIALIZADO,
				Modulo.ID_MODULO_PSICOSSOCIAL_PARA_AS_FAMILIAS };
	}
	
	private boolean possuiExcessoFaltasNaoJustificadasCTO(Usuario usuario) {
		boolean possuiExcessoFaltaNaoJustificadas = false;
		List<Map<String, Object>> frequenciasAAvaliar = obterFrequenciasAAvaliar(usuario);	
		
		possuiExcessoFaltaNaoJustificadas = contabilizarFaltasNaoJustificadas(frequenciasAAvaliar) >= EXCESSO_FALTAS;
		GravadorTexto.gravarHistoricoSeNecessario(possuiExcessoFaltaNaoJustificadas, usuario,
				frequenciasAAvaliar,
				Configuracao.DIRETORIO_PRONTUARIOS_ESPERA_EXCESSO_FALTAS);
		return possuiExcessoFaltaNaoJustificadas;
	}

	private List<Map<String, Object>> obterFrequenciasAAvaliar(Usuario usuario) {
		List<Map<String, Object>> frequenciasAAvaliar = repositorioSislara
				.obterFrenquenciaPorProntuarioExcluindoArea(
						usuario != null ? usuario.getId() : null,
						Area.LIBERACAO_INCLUSAO_NA_ESPERA_POR_EXCESSO_DE_FALTA);

		removerFrequencias(
				frequenciasAAvaliar,
				obterSomenteFrequenciasDoModuloPsicossocialParaFamiliasComLancamentosNoMesmoDiaEmOutroModulo(
						usuario,
						DescricaoTipoAtendimento.ID_ATENDIMENTO_ESPECIALIZADO_GLOBAL,
						obterIdentificacoesDosModulosAtendimentoEspecificoEspecializadoEPsicossocialParaAsFamilias()));
		removerFrequencias(
				frequenciasAAvaliar,
				obterSomenteFrequenciasDoModuloPsicossocialParaFamiliasComLancamentosNoMesmoDiaEmOutroModulo(
						usuario,
						DescricaoTipoAtendimento.ID_AE_TRANSTORNO_GLOBAL_DO_DESENVOLVIMENTO,
						obterIdentificacoesDosModulosAtendimentoEspecificoEspecializadoEPsicossocialParaAsFamilias()));
		removerFrequencias(
				frequenciasAAvaliar,
				obterSomenteFrequenciasDoModuloPsicossocialParaFamiliasComLancamentosNoMesmoDiaEmOutroModulo(
						usuario,
						DescricaoTipoAtendimento.ID_AE_COMUNICACAO,
						obterIdentificacoesDosModulosAtendimentoEspecificoEspecializadoEPsicossocialParaAsFamilias()));	
		removerFrequencias(
				frequenciasAAvaliar,
				obterSomenteFrequenciasDoModuloPsicossocialParaFamiliasComLancamentosNoMesmoDiaEmOutroModulo(
						usuario,
						DescricaoTipoAtendimento.ID_AE_ATIVIDADES_DE_VIDA_AUTONOMA,
						obterIdentificacoesDosModulosAtendimentoEspecificoEspecializadoEPsicossocialParaAsFamilias()));
		return frequenciasAAvaliar;
	}
	
	private List<Map<String, Object>> obterSomenteFrequenciasDoModuloPsicossocialParaFamiliasComLancamentosNoMesmoDiaEmOutroModulo(
			Usuario usuario, Long idDescricaoTipoAtendimento, Long... idModulos) {
		List<Map<String, Object>> frequenciasDosModulosNoMesmoDia = repositorioSislara
				.obterFrenquenciaDeModulosNoMesmoDia(
						usuario != null ? usuario.getId() : null, idDescricaoTipoAtendimento, idModulos);
		List<Map<String, Object>> frequenciaNoMesmoDiaAFiltrar = new CopyOnWriteArrayList<Map<String, Object>>(
				frequenciasDosModulosNoMesmoDia);
		manterApenasFrequenciaDoModuloPsicossocialParaFamilias(frequenciaNoMesmoDiaAFiltrar);
		return frequenciaNoMesmoDiaAFiltrar;
	}
	
	private int contabilizarFaltasNaoJustificadas(List<Map<String, Object>> frequenciasAAvaliar){
		int totalFaltasNaoJustificadas = 0;
		int contador = 0;
		for (Map<String, Object> frequencia : frequenciasAAvaliar) {
			if (contador < EXCESSO_FALTAS
					&& frequencia.get("frequencia").equals(
							Frequencia.FU.toString())) {
				totalFaltasNaoJustificadas++;
			}
			contador++;
		}
		return totalFaltasNaoJustificadas;
	}
	
	private void removerFrequencias(
			List<Map<String, Object>> frequenciasReferencia,
			List<Map<String, Object>> frequenciasARemover) {
		for(Map<String, Object> frequenciaARemover : frequenciasARemover){
			frequenciasReferencia.remove(frequenciaARemover);
		}
	}
	
	private void manterApenasFrequenciaDoModuloPsicossocialParaFamilias(List<Map<String, Object>> lista) {
		for (Map<String, Object> atendimentoPsicossocialParaFamilia : lista) {
			if (!atendimentoPsicossocialParaFamilia.get("id_modulo").equals(
					Modulo.ID_MODULO_PSICOSSOCIAL_PARA_AS_FAMILIAS)) {
				lista.remove(atendimentoPsicossocialParaFamilia);
			}
		}
	}

	public Resultado processarAtendimentoGrupoInclusaoNaListaDeEsperaPorExecessoDeFaltas(
			AtendimentoGrupo atendimentosGrupo, ContaAcesso contaAcessoReponsavel) {
		Resultado resultado = new Resultado();
		for (AtendimentoUsuario atendimentoUsuario : atendimentosGrupo.getAtendimentosUsuariosComFrequenciaDiferenteDeAT()) {
			if (processarInclusaoNaListaDeEsperaPorExecessoDeFaltasNaoJustificadas(atendimentoUsuario.getUsuario(), contaAcessoReponsavel)) {
				resultado.setMensagem(obterMensagem(atendimentoUsuario.getUsuario()));
			}
		}
		return resultado;
	}

	public Resultado processarAtendimentoIndividualInclusaoNaListaDeEsperaPorExecessoDeFaltas(
			AtendimentoIndividual atendimentoIndividual, ContaAcesso contaAcesso) {
		Resultado resultado = new Resultado();
		if (atendimentoIndividual.possuiUsuario() && processarInclusaoNaListaDeEsperaPorExecessoDeFaltasNaoJustificadas(
				atendimentoIndividual.getUsuario(), contaAcesso)) {
			resultado.setMensagem(obterMensagem(atendimentoIndividual.getUsuario()));
		}
		return resultado;
	}
	
	private String obterMensagem(Usuario usuario) {
		return "<br>ATENÇÃO:\n"
				+ "<br>BLOQUEIO do prontuário "
				+ usuario.getId()
				+ " - "
				+ usuario.getInformacaoEssencial().getNome()
				+ " por faltas axcessivas. "
				+ "<br>AGENDAR com o Servico Social para justificar. ";
	}
	
	public static boolean verificarSePossuiEsperaAguardandoOuJaExistiuEsperaComMesmaChaveDeAtendimento(Espera espera, RepositorioEspera repositorioEspera) {
		boolean resultado = false;

		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera.setDescricaoTipoAtendimento(espera
				.getDescricaoTipoAtendimento());
		especificacaoPesquisaEspera.setModulo(espera.getModulo());
		if (espera.getUsuario() != null) {
			especificacaoPesquisaEspera.setProntuario(espera.getUsuario()
					.getId().toString());
		}

		List<Espera> esperasAguardandoExistentes = repositorioEspera
				.obterPor(especificacaoPesquisaEspera);
		for (Espera esperaExistente : esperasAguardandoExistentes) {
			if ((espera.possuiUsuario() && esperaExistente.possuiUsuario()
					&& espera.getUsuario().equals(esperaExistente.getUsuario()) && !espera.equals(esperaExistente))) {
				if (esperaExistente.estaAguardando()) {
					resultado = true;
				} else if (espera.possuiMesmaChaveAtendimentoAcarretouInclusao(esperaExistente)) {
					resultado = true;
				}
			}
		}
		return resultado;
	}
	
	public static boolean verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasEAgendamentoNaoEServicoSocialAvaliacaoETriagem(
			Espera espera, Agendamento agendamento, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		return espera.possuiUsuario()
				&& possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(espera.getUsuario().getId(),
						repositorioEspera, repositorioTipoAtendimento)
				&& !eDescricaoServicoSocialEModuloAvaliacaoETriagem(agendamento.getDescricaoTipoAtendimento(),
						agendamento.getModulo());
	}
	
	public static boolean verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEDescricaoServicoSocialEModuloAvaliacaoETriagem(
			Espera espera, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		return verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
				espera.getUsuario(), espera.getDescricaoTipoAtendimento(),
				espera.getModulo(), repositorioEspera,
				repositorioTipoAtendimento);
	}
	
	public static boolean verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
			Agendamento agendamento, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		return verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
				agendamento.getUsuario(),
				agendamento.getDescricaoTipoAtendimento(),
				agendamento.getModulo(), repositorioEspera,
				repositorioTipoAtendimento);
	}
	
	public synchronized static boolean verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento,
			RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		return verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
				especificacaoGeracaoAgendamento.getUsuario(),
				especificacaoGeracaoAgendamento.getDescricaoTipoAtendimento(),
				especificacaoGeracaoAgendamento.getModulo(), repositorioEspera,
				repositorioTipoAtendimento);
	}
	
	private static boolean verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
			Usuario usuario, DescricaoTipoAtendimento descricaoTipoAtendimento,
			Modulo modulo, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		boolean possuiEspera = false;
		boolean avaliacaoETriagem = false;
		if (usuario != null) {
			if (possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(usuario.getId(), repositorioEspera,
					repositorioTipoAtendimento)) {
				possuiEspera = true;
			}
			avaliacaoETriagem = eDescricaoServicoSocialEModuloAvaliacaoETriagem(descricaoTipoAtendimento, modulo);
		} else {
			return false;
		}
		return possuiEspera && !avaliacaoETriagem;
	}

	private static boolean eDescricaoServicoSocialEModuloAvaliacaoETriagem(
			DescricaoTipoAtendimento descricaoTipoAtendimento, Modulo modulo) {
		return descricaoTipoAtendimento.eServicoSocial() && modulo.eAvaliacaoETriagem();
	}

	public static boolean verificarSeExisteEsperaSimultaneamenteEmAtendimentoEspecificoEspecializadoETriagemOftalmologicaDaAvaliacaoDeServicoEmOftalmologia(
			Espera espera, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		List<Espera> esperas = obterEsperaNoAtendimentoEspecificoEspecializadoETriagemOftalmologicaDaAvaliacaoDeServicoEmOftalmologia(
				espera, repositorioEspera, repositorioTipoAtendimento);
		return esperas.size() > 0
				&& (espera
						.getDescricaoTipoAtendimento().eAvaliacaoServicoAtencaoEspecializadaOftalmologia() 
						&& (espera.getModulo().eTriagemOftalmologica() || espera.getModulo().eAtendimentoEspecificoEspecializado()));
	}

	public static List<Espera> obterEsperaNoAtendimentoEspecificoEspecializadoETriagemOftalmologicaDaAvaliacaoDeServicoEmOftalmologia(
			Espera espera, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		Set<Espera> esperas = new HashSet<>();
		if (espera.getUsuario() != null) {
			EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
			especificacaoPesquisaEspera
					.setDescricaoTipoAtendimento(repositorioTipoAtendimento
							.obterDescricaoAvaliacaoDeServicoEmOftalmologia());
			especificacaoPesquisaEspera.setModulo(repositorioTipoAtendimento
					.obterModuloAtendimentoEspecificoEspecialidado());
			especificacaoPesquisaEspera
					.setProntuario(espera.getUsuario().getId().toString());
			especificacaoPesquisaEspera
					.setStatusEspera(StatusEspera.AGUARDANDO);
			esperas.addAll(repositorioEspera
					.obterPor(especificacaoPesquisaEspera));

			especificacaoPesquisaEspera.setModulo(repositorioTipoAtendimento
					.obterModuloTriagemOftalmologica());
			especificacaoPesquisaEspera.setStatusEspera(StatusEspera.TRIANDO);
			esperas.addAll(repositorioEspera
					.obterPor(especificacaoPesquisaEspera));
			
			especificacaoPesquisaEspera.setStatusEspera(StatusEspera.AGUARDANDO);
			esperas.addAll(repositorioEspera
					.obterPor(especificacaoPesquisaEspera));
		}
		esperas.remove(espera);
		return new ArrayList<Espera>(esperas);
	}

	public void cancelarTodasEsperasAguardandoDevidoExpiracaoDoRetorno(
			Usuario usuario) {
		EspecificacaoPesquisaEspera especificacaoPesquisaEsperaAguardando = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEsperaAguardando.setProntuario(usuario.getId()
				.toString());
		especificacaoPesquisaEsperaAguardando
				.setStatusEspera(StatusEspera.AGUARDANDO);
		List<Espera> esperasAguardando = repositorioEspera
				.obterPor(especificacaoPesquisaEsperaAguardando);
		for (Espera esperaAguardando : esperasAguardando) {
			logger.info("Remoção de todas as Esperas aguardando devido expiração de retorno iniciada.");
					esperaAguardando.cancelar(ContaAcesso.obterAcessoRoot());
			esperaAguardando
					.setJustificativaCancelamento("Cancelamento efetuado automaticamente devido expiração de período de retorno.");
			repositorioEspera.salvar(esperaAguardando);
		}
	}

	public void incluirNaListaDeEsperaDeAvaliacaoFuncionalSeNecessario(AtendimentoIndividual atendimentoIndividual) {
		if (atendimentoIndividual.possuiUsuario()
				&& (atendimentoIndividual
						.eDescricaoAvaliacaoServicoAtencaoEspecializadaOftalmologiaModuloAtendimentoEspecificoEspecializado()
						|| atendimentoIndividual
								.eDescricaoAcompanhamentoServicoAtencaoEspecializadaOftalmologiaModuloAtendimentoEspecificoEspecializado())
				&& atendimentoIndividual.ePrimeiraVez()
				&& !atendimentoIndividual.naoECasoParaLM()) {
			Usuario usuario = atendimentoIndividual.getUsuario();

			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
			especificacaoPesquisaAtendimentoIndividual
					.setProntuario(atendimentoIndividual.getUsuario().getId().toString());
			especificacaoPesquisaAtendimentoIndividual.setDescricaoTipoAtendimento(
					DescricaoTipoAtendimento.fabricarAvaliacaoServicoAtencaoEspecializadaOftalmologia());
			especificacaoPesquisaAtendimentoIndividual
					.setModulo(Modulo.fabricaModuloAtendimentoEspecificoEspecializado());

			long quantidadeAtendimentoIndividualComDescricaoAvaliacaoServicoAtencaoEspecializadaOftalmologiaModuloAEEeFrequenciaAT = repositorioAtendimentoIndividual
					.obterPor(especificacaoPesquisaAtendimentoIndividual).stream()
					.filter(atendimentoIndividualDeAvaliacaoOftalmologiaModuloAEE -> atendimentoIndividualDeAvaliacaoOftalmologiaModuloAEE
							.getInformacaoAtendimento().estaComFrenquenciaAT()
							&& !atendimentoIndividual.estaCancelado())
					.count();
			if (quantidadeAtendimentoIndividualComDescricaoAvaliacaoServicoAtencaoEspecializadaOftalmologiaModuloAEEeFrequenciaAT == 1) {
				Espera espera = new Espera();
				espera.atribuirDataAtualNaDataExpectativa();
				inserirNaListaDeEsperaDeAvaliacaoFuncional(espera, usuario,
						"Incluído automaticamente em consequência ao primeiro atendimento em oftalmologia.",
						Setor.NENHUM, TipoEspera.CN);
			}
		}
	}

	public ResultadoInclusaoListaEsperaAvaliacaoFuncional inserirNaListaDeEsperaDeAvaliacaoFuncional(Espera espera, Usuario usuario, String obs, Setor setor,
			TipoEspera tipoEspera) {
		ResultadoInclusaoListaEsperaAvaliacaoFuncional resultadoInclusaoListaEsperaAvaliacaoFuncional = new ResultadoInclusaoListaEsperaAvaliacaoFuncional();
		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera
				.setDescricaoTipoAtendimento(repositorioTipoAtendimento.obterDescricaoTipoAvaliacaoFuncional());
		especificacaoPesquisaEspera
				.setModulo(repositorioTipoAtendimento.obterModuloAtendimentoEspecificoEspecialidado());
		especificacaoPesquisaEspera.setStatusEspera(StatusEspera.AGUARDANDO);
		especificacaoPesquisaEspera.setProntuario(usuario.getId().toString());

		if (repositorioEspera.obterPor(especificacaoPesquisaEspera).isEmpty()) {
			espera.setUsuario(usuario);
			espera.setDescricaoTipoAtendimento(repositorioTipoAtendimento.obterDescricaoTipoAvaliacaoFuncional());
			espera.setModulo(repositorioTipoAtendimento.obterModuloAtendimentoEspecificoEspecialidado());
			espera.setContaAcessoOperacao(ContaAcesso.obterAcessoRoot());
			espera.setObs(obs);
			espera.setSetor(setor);
			espera.setTipoEspera(tipoEspera);
			espera.validarObrigatoriedadeETamanhoMaximoDeDados();

			if (espera.validado()) {
				repositorioEspera.salvar(espera);
				resultadoInclusaoListaEsperaAvaliacaoFuncional.efetuadoComSucesso(espera);
			}else{
				resultadoInclusaoListaEsperaAvaliacaoFuncional.adicionarErro(espera.obterDescricaoErros());
			}
		}else{
			resultadoInclusaoListaEsperaAvaliacaoFuncional.adicionarErro("Já existe na lista de espera de avaliação funcional.");
		}
		return resultadoInclusaoListaEsperaAvaliacaoFuncional;
	}

	public void cancelarEsperasDeAvaliacaoCTOComDataDeExpectativaExpiradaHaMaisDe2AnosComUsuariosMenoresDe21AnosESemNenhumAtendimentoNoPeriodo() {
		logger.info("Cancelamento de esperas iniciada.");
		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera.setStatusEspera(StatusEspera.AGUARDANDO);
		List<Espera> esperaDeAvaliacaoCTOComDataExpectativaExpiradaHaMaisDe2AnosComUsuariosMenoresDe21Anos = repositorioEspera
				.obterPor(especificacaoPesquisaEspera).stream()
				.filter(espera -> espera.possuiAlgumaDescricaoOuModuloDeAvaliacaoCTO()
						&& espera.possuiMaisDe2AnosTranscorridosNaDataDeExpectativa() && espera.possuiUsuarioComMenos21Anos()
						&& !repositorioSislara.possuiAlgumAtendimentoNosUltimosDoisAnos(espera))
				.collect(Collectors.toList());

		for (Espera esperaAguardando : esperaDeAvaliacaoCTOComDataExpectativaExpiradaHaMaisDe2AnosComUsuariosMenoresDe21Anos) {
			logger.info("Cancelamento de espera de avaliação CTO expirada há 2 anos iniciada.");
			esperaAguardando.cancelar(ContaAcesso.obterAcessoRoot());
			esperaAguardando.setJustificativaCancelamento(
					"Em caso de interesse agendar SOMENTE SERVIÇO SOCIAL. Cancelamento efetuado automaticamente na espera de avaliação CTO com data de expectativa expirada há 2 anos, com usuário menor de 21 anos, sem nenhum atendimento no período. ");
			repositorioEspera.salvar(esperaAguardando);
		}
		logger.info("Cancelamento de esperas finalizada.");
	}
}
