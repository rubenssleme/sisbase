package br.laramara.ti.sislaraserver.fachadas;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemMotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemStatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendador;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.AgendamentoComDataAnteriorADataAtual;
import br.laramara.ti.sislaraserver.dominio.agenda.AgendamentoEmConflitoException;
import br.laramara.ti.sislaraserver.dominio.agenda.AgendamentoNaoPodeSerCopiadoException;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoCopiaAgendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;
import br.laramara.ti.sislaraserver.dominio.agenda.RestricaoAssociacaoPreCadastro;
import br.laramara.ti.sislaraserver.dominio.agenda.StatusAgendamento;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.fabricas.FabricaAgendamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoGeracaoCopiaAgendamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaMotivoCancelamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatus;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusAgendamento;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoAgendamentoCancelamento;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoAgendamentoEdicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMotivoCancelamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

@Component
public class FachadaAgendamento extends Fachada {

	@Inject
	private Agendador agendador;
	@Inject
	private RepositorioAgendamento repositorioAgendamento;
	@Inject
	private RepositorioBloqueio repositorioBloqueio;
	@Inject
	private RepositorioMotivoCancelamento repositorioMotivoCancelamento;
	@Inject
	private RepositorioEspera repositorioEspera;
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	@Inject
	private RepositorioPendencia repositorioPendencia;

	public synchronized ResultadoEdicaoAgendamentoDTO liberarAgendamentoColocandoStatusCancelando(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto) {
		return liberarAgendamento(agendamentoDto, StatusAgendamento.CANCELADO,
				tokenDto);
	}
	
	public synchronized ResultadoEdicaoAgendamentoDTO liberarAgendamentoColocandoStatusReagendado(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto) {
		return liberarAgendamento(agendamentoDto, StatusAgendamento.REAGENDADO,
				tokenDto);
	}
	
	private synchronized ResultadoEdicaoAgendamentoDTO liberarAgendamento(
			AgendamentoDTO agendamentoDto,
			StatusAgendamento statusAgendamentoAposLiberacao, TokenDTO tokenDto) {
		ResultadoEdicaoAgendamentoDTO resultadoDto = new ResultadoEdicaoAgendamentoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.AGENDA_EDICAO)) {
			Agendamento agendamento = new FabricaAgendamento()
					.converterParaDominio(agendamentoDto,
							repositorioAgendamento.obterPorId(agendamentoDto
									.getId()));
			if (!repositorioBloqueio.existeBloqueioAgendamento(agendamento)) {
				try {
					resultadoDto.efetuadoComSucesso(new FabricaAgendamento()
							.converterParaDTO(agendador
									.liberarAgendamento(agendamento,
											statusAgendamentoAposLiberacao,
											contaAcesso)));
					logger.info(contaAcesso
							+ " efetuou Liberação de Agendamento "
							+ agendamento + " com sucesso.");
				} catch (Exception e) {
					logger.info("Erro durante liberação de " + agendamento
							+ ". \nDetalhes: " + e.getMessage());
					resultadoDto.adicionarErro(e.getMessage());
				}
			} else {
				resultadoDto.adicionarErro(MENSAGEM_MODULO_BLOQUEADO);
			}
		} else {
			resultadoDto.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoDto;
	}

	public synchronized ResultadoGeracaoAgendamentoDTO gerarAgendamento(
			EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto,
			TokenDTO tokenDto) {
		ResultadoGeracaoAgendamentoDTO resultado = new ResultadoGeracaoAgendamentoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.AGENDA_EDICAO)) {
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento = new FabricaEspecificacaoGeracaoAgendamento()
					.converterParaDominio(especificacaoGeracaoAgendamentoDto);
			especificacaoGeracaoAgendamento
					.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (especificacaoGeracaoAgendamento.validado()) {
				if (!repositorioBloqueio.existeBloqueioAgendamento(
						especificacaoGeracaoAgendamento
								.getDescricaoTipoAtendimento(),
						especificacaoGeracaoAgendamento.getModulo())) {
					if (!AutomatizadorEspera.verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
							especificacaoGeracaoAgendamento, repositorioEspera,
							repositorioTipoAtendimento))
						try {
							List<Agendamento> agendamentosGerados = agendador
									.gerarAgendamentos(
											especificacaoGeracaoAgendamento,
											contaAcesso);
							logger.info(contaAcesso
									+ " efetuou Geração de Agendamento para "
									+ especificacaoGeracaoAgendamento
									+ " com sucesso.");
							resultado
									.efetuadoComSucesso(new FabricaAgendamento()
											.converterParaDTO(agendamentosGerados));
						} catch (AgendamentoEmConflitoException
								| AgendamentoComDataAnteriorADataAtual e) {
							resultado.adicionarErro(e.getMessage());
						}
					else {
						resultado
								.adicionarErro(MENSAGEM_BLOQUEIO_POR_FALTAS_EXCESSIVAS);
					}
				} else {
					resultado.adicionarErro(MENSAGEM_MODULO_BLOQUEADO);
				}
			} else {
				resultado.adicionarErro(especificacaoGeracaoAgendamento
						.obterDescricaoErros());
			}
		} else {
			resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultado;
	}

	public synchronized ResultadoEdicaoAgendamentoDTO copiarAgendamento(
			EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDto,
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto) {
		ResultadoEdicaoAgendamentoDTO resultado = new ResultadoEdicaoAgendamentoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.AGENDA_EDICAO)) {
			EspecificacaoGeracaoCopiaAgendamento especificacaoGeracaoCopiaAgendamento = new FabricaEspecificacaoGeracaoCopiaAgendamento()
					.converterParaDominio(especificacaoGeracaoCopiaAgendamentoDto);
			especificacaoGeracaoCopiaAgendamento
					.validarObrigatoriedadeETamanhoMaximoDeDados();
			Agendamento agendamentoACopiar = new FabricaAgendamento()
					.converterParaDominio(agendamentoDto,
							repositorioAgendamento.obterPorId(agendamentoDto
									.getId()));
			if (!repositorioBloqueio
					.existeBloqueioAgendamento(agendamentoACopiar)) {
				try {
					if (especificacaoGeracaoCopiaAgendamento.validado()) {
						Agendamento agendamento = agendador.copiarAgendamento(
								especificacaoGeracaoCopiaAgendamento,
								agendamentoACopiar, contaAcesso);
						logger.info(contaAcesso + " efetuou Copia de "
								+ agendamento + " para "
								+ especificacaoGeracaoCopiaAgendamento
								+ " com sucesso.");
						resultado.efetuadoComSucesso(new FabricaAgendamento()
								.converterParaDTO(agendamento));
					} else {
						resultado
								.adicionarErro(especificacaoGeracaoCopiaAgendamento
										.obterDescricaoErros());
					}
				} catch (AgendamentoNaoPodeSerCopiadoException
						| AgendamentoComDataAnteriorADataAtual
						| AgendamentoEmConflitoException e) {
					resultado.adicionarErro(e.getMessage());
				}
			} else {
				resultado.adicionarErro(MENSAGEM_MODULO_BLOQUEADO);
			}
		} else {
			resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultado;
	}

	public synchronized ResultadoEdicaoAgendamentoDTO editarAgendamentoSemRestricaoAssociacaoPreCadastro(
			boolean originadoPorExcessoDeFaltas, AgendamentoDTO agendamentoDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoAgendamentoEdicao(originadoPorExcessoDeFaltas, new FabricaAgendamento(),
				repositorioAgendamento, repositorioEspera, repositorioTipoAtendimento, repositorioPendencia,
				agendamentoDto, RestricaoAssociacaoPreCadastro.NAO);
		return executarEdicao(operacao, tokenDto);
	}
	
	public synchronized ResultadoEdicaoAgendamentoDTO editarAgendamento(AgendamentoDTO agendamentoDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoAgendamentoEdicao(false, new FabricaAgendamento(),
				repositorioAgendamento, repositorioEspera, repositorioTipoAtendimento, repositorioPendencia,
				agendamentoDto, RestricaoAssociacaoPreCadastro.SIM);
		return executarEdicao(operacao, tokenDto);
	}
	
	private ResultadoEdicaoAgendamentoDTO executarEdicao(
			OperacaoFachada operacao, TokenDTO tokenDto) {
		return (ResultadoEdicaoAgendamentoDTO) verificarPermissaoEProcessar(
				operacao, Permissao.AGENDA_EDICAO,
				new ResultadoEdicaoAgendamentoDTO(), tokenDto);
	}

	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAgendamento() {
		ResultadoListagemStatusDTO resultado = new ResultadoListagemStatusDTO();
		resultado.efetuadoComSucesso(new FabricaStatus()
				.converterParaDTO(Status.statusPassivosDeAgendamento()));
		return resultado;
	}
	
	public ResultadoListagemAgendamentoDTO obterListagemAgendamento(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto) {
		ResultadoListagemAgendamentoDTO resultadoDto = new ResultadoListagemAgendamentoDTO();

		EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento = new FabricaEspecificacaoPesquisaAgendamento()
				.converterParaDominio((EspecificacaoPesquisaAgendamentoDTO)especificacaoPesquisaAgendamentoDto);
		especificacaoPesquisaAgendamento
				.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		if (especificacaoPesquisaAgendamento.validado()) {
			resultadoDto = (ResultadoListagemAgendamentoDTO) obterListagem(
					repositorioAgendamento
							.obterPor(especificacaoPesquisaAgendamento),
					new FabricaAgendamento(), "Agendamentos",
					new ResultadoListagemAgendamentoDTO());
		} else {
			resultadoDto.adicionarErro(especificacaoPesquisaAgendamento
					.obterDescricaoErros());
		}
		return resultadoDto;
	}

	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoAgendamento() {
		return (ResultadoListagemMotivoCancelamentoDTO) obterListagem(
				repositorioMotivoCancelamento.obterTodosAgendamento(),
				new FabricaMotivoCancelamento(), "Motivos de Cancelamento",
				new ResultadoListagemMotivoCancelamentoDTO());
	}

	public ResultadoListagemStatusAgendamentoDTO obterListagemStatusAgendamento() {
		return (ResultadoListagemStatusAgendamentoDTO) obterListagem(
				Arrays.asList(StatusAgendamento.values()),
				new FabricaStatusAgendamento(), "Status de Agendamento",
				new ResultadoListagemStatusAgendamentoDTO());
	}

	public ResultadoListagemAgendamentoDTO obterListagemAgendamentoDisponiveis(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto) {
		especificacaoPesquisaAgendamentoDto
				.setStatusAgendamentoDTO(new FabricaStatusAgendamento()
						.converterParaDTO(StatusAgendamento.DISPONIVEL));
		especificacaoPesquisaAgendamentoDto.marcarDataFutura();
		return obterListagemAgendamento(especificacaoPesquisaAgendamentoDto);
	}

	public synchronized ResultadoEdicaoAgendamentoDTO cancelarAgendamento(
			AgendamentoDTO agendamentoDTO, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoAgendamentoCancelamento(
				new FabricaAgendamento(), repositorioAgendamento,
				agendamentoDTO);
		return (ResultadoEdicaoAgendamentoDTO) verificarPermissaoEProcessar(
				operacao, Permissao.AGENDA_EDICAO,
				new ResultadoEdicaoAgendamentoDTO(), tokenDto);
	}

	public synchronized ResultadoEdicaoAgendamentoDTO reagendarAgendamento(
			AgendamentoDTO agendamentoAntigoDto,
			AgendamentoDTO agendamentoNovoDto, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		ResultadoEdicaoAgendamentoDTO resultadoLiberacaoAgendamentoDTO = new ResultadoEdicaoAgendamentoDTO();
		Agendamento agendamentoAntigo = repositorioAgendamento
				.obterPorId(agendamentoAntigoDto.getId());
		Agendamento agendamentoNovo = repositorioAgendamento
				.obterPorId(agendamentoNovoDto.getId());
		if (!agendamentoAntigo.expirouLimiteDe24Horas()) {
			if (agendamentoAntigo.estaAgendado() && agendamentoNovo.estaDisponivel()
					&& agendamentoAntigo.possuiDescricaoEModuloIdentico(agendamentoNovo)) {
				logger.info(contaAcesso + " iniciou o reagendamento.");
				agendamentoNovoDto.setUsuarioDto(agendamentoAntigoDto.getUsuarioDto());
				agendamentoNovoDto.setPreCadastroDto(agendamentoAntigoDto.getPreCadastroDto());
				ResultadoEdicaoAgendamentoDTO resultadoReagendamento = editarAgendamento(agendamentoNovoDto, tokenDto);
				if (resultadoReagendamento.sucesso()) {
					agendamentoAntigoDto.setMotivoCancelamentoDTO(
							new FabricaMotivoCancelamento().converterParaDTO(repositorioMotivoCancelamento.obterPorId(
									MotivoCancelamento.ID_MOTIVO_CANCELAMENTO_LIGOU_E_PEDIU_PARA_CANCELAR)));
					agendamentoAntigoDto
							.setJustificativaCancelamento("Cancelamento automático em decorrência de reagendamento.");
					resultadoLiberacaoAgendamentoDTO = liberarAgendamentoColocandoStatusReagendado(agendamentoAntigoDto,
							tokenDto);
					logger.info(contaAcesso + " concluiu o reagendamento com sucesso.");
				} else {
					resultadoLiberacaoAgendamentoDTO.adicionarErro(resultadoReagendamento.obterMensagens());
				}
			} else {
				resultadoLiberacaoAgendamentoDTO.adicionarErro("Não foi possível efetuar o reagendamento.");
			}
		} else {
			resultadoLiberacaoAgendamentoDTO.adicionarErro(
					"Prazo máximo de 24 horas para efetuar o reagendamento está expirado. O usuário deverá retornar para a lista de espera.");
		}
		return resultadoLiberacaoAgendamentoDTO;
	}
}
