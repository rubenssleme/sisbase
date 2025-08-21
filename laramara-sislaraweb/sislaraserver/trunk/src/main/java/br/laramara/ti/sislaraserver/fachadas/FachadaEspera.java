package br.laramara.ti.sislaraserver.fachadas;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoAssociacaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoEdicaoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemStatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemTipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaAgendamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspera;
import br.laramara.ti.sislaraserver.fabricas.FabricaInformacaoEssencial;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusEspera;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoEspera;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEsperaAgendamento;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEsperaCancelamento;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEsperaEdicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

@Component
public class FachadaEspera extends Fachada {

	@Inject
	private RepositorioEspera repositorioEspera;
	@Inject
	private RepositorioBloqueio repositorioBloqueio;
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	@Inject
	private RepositorioAgendamento repositorioAgendamento;
	@Inject
	private FachadaAgendamento fachadaAgendamento;
	
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaTotal() {
		return (ResultadoListagemTipoEsperaDTO) obterListagem(
				Arrays.asList(TipoEspera.values()), new FabricaTipoEspera(),
				"Tipo de Espera Total", new ResultadoListagemTipoEsperaDTO());
	}
	
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaDisponiveisParaInclusao() {
		return (ResultadoListagemTipoEsperaDTO) obterListagem(
				Arrays.asList(TipoEspera.values()), new FabricaTipoEspera(),
				"Tipo de Espera para Edição",
				new ResultadoListagemTipoEsperaDTO());
	}

	public ResultadoListagemStatusEsperaDTO obterListagemStatusEspera() {
		return (ResultadoListagemStatusEsperaDTO) obterListagem(
				Arrays.asList(StatusEspera.values()),
				new FabricaStatusEspera(), "Status de Espera",
				new ResultadoListagemStatusEsperaDTO());
	}

	public ResultadoListagemEsperaDTO obterListagemEspera(
			EspecificacaoPesquisaEsperaDTO especificacao) {
		ResultadoListagemEsperaDTO resultadoDto = new ResultadoListagemEsperaDTO();

		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new FabricaEspecificacaoPesquisaEspera()
				.converterParaDominio((EspecificacaoPesquisaEsperaDTO) especificacao);
		especificacaoPesquisaEspera
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (especificacaoPesquisaEspera.validado()) {
			resultadoDto = (ResultadoListagemEsperaDTO) obterListagem(
					repositorioEspera.obterPor(especificacaoPesquisaEspera),
					new FabricaEspera(), "Espera", new ResultadoListagemEsperaDTO());
		} else {
			resultadoDto.adicionarErro(especificacaoPesquisaEspera
					.obterDescricaoErros());
		}
		return resultadoDto;
	}
	
	public String obterObservacoesHistoricasEspera(
			InformacaoEssencialDTO informacaoEssencialDto) {
		return repositorioEspera
				.obterObsHistoricas(new FabricaInformacaoEssencial()
						.converterParaDominio(informacaoEssencialDto));
	}
	
	public synchronized ResultadoEdicaoEsperaDTO editarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEsperaEdicao(new FabricaEspera(),
				repositorioEspera, repositorioBloqueio, repositorioTipoAtendimento, esperaDto);
		return (ResultadoEdicaoEsperaDTO) verificarPermissaoEProcessar(
				operacao, Permissao.ESPERA_EDICAO,
				new ResultadoEdicaoEsperaDTO(), tokenDto);
	}

	public synchronized ResultadoEdicaoEsperaDTO cancelarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEsperaCancelamento(
				new FabricaEspera(), repositorioEspera, esperaDto, false);
		return (ResultadoEdicaoEsperaDTO) verificarPermissaoEProcessar(
				operacao, Permissao.ESPERA_EDICAO,
				new ResultadoEdicaoEsperaDTO(), tokenDto);
	}
	
	public synchronized ResultadoEdicaoEsperaDTO agendarEspera(EsperaDTO esperaDto,
			boolean obsAutomaticaDeAssociacao, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEsperaAgendamento(new FabricaEspera(),
				repositorioEspera, esperaDto, obsAutomaticaDeAssociacao);
		return (ResultadoEdicaoEsperaDTO) verificarPermissaoEProcessar(
				operacao, Permissao.ESPERA_EDICAO,
				new ResultadoEdicaoEsperaDTO(), tokenDto);
	}

	public synchronized ResultadoEdicaoEsperaDTO editarEsperaEAssociarAgendamento(
			EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDto, TokenDTO tokenDto) {

		ResultadoEdicaoEsperaDTO resultadoDto = new ResultadoEdicaoEsperaDTO();

		ResultadoEdicaoEsperaDTO resultadoEdicaoEsperaDto = new ResultadoEdicaoEsperaDTO();
		ResultadoEdicaoAgendamentoDTO resultadoAssociacaoAgendamentoDto = new ResultadoEdicaoAgendamentoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		logger.info(contaAcesso + " solicitou Edição de Espera e Associação de Agendamento.");
		EsperaDTO esperaDto = especificacaoAssociacaoAgendamentoDto.getEsperaDto();
		AgendamentoDTO agendamentoDTO = especificacaoAssociacaoAgendamentoDto.getAgendamentoDto();

		if (contaAcesso.possuiPermissao(Permissao.ESPERA_EDICAO)
				&& contaAcesso.possuiPermissao(Permissao.AGENDA_EDICAO)) {
			Espera esperaObtido = repositorioEspera.obterPorId(esperaDto.getId());
			esperaObtido.setContaAcessoOperacao(contaAcesso);
			esperaObtido.validarObrigatoriedadeETamanhoMaximoDeDados();
			Agendamento agendamentoObtido = new FabricaAgendamento().converterParaDominio(agendamentoDTO,
					repositorioAgendamento.obterPorId(agendamentoDTO.getId()));
			agendamentoObtido.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (agendamentoObtido.validado() && esperaObtido.validado()) {
				if (esperaObtido.estaAguardando() && agendamentoObtido.estaDisponivel()) {
					if (!AutomatizadorEspera
							.verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasEAgendamentoNaoEServicoSocialAvaliacaoETriagem(
									esperaObtido, agendamentoObtido, repositorioEspera, repositorioTipoAtendimento)) {
						if (!AutomatizadorEspera.verificarSeJaPossuiAgendamentoFuturoComMesmaDescricaoTipoAtendimentoEModulo(esperaObtido, agendamentoObtido, repositorioAgendamento)) {
							agendamentoObtido.setUsuario(esperaObtido.getUsuario());
							agendamentoObtido.setPreCadastro(esperaObtido.getPreCadastro());
							agendamentoObtido.validarObrigatoriedadeETamanhoMaximoDeDados();
							if (agendamentoObtido.validado()) {
								resultadoEdicaoEsperaDto = agendarEspera(new FabricaEspera().converterParaDTO(esperaObtido),
										true, tokenDto);
								if (resultadoEdicaoEsperaDto.sucesso()) {
									resultadoAssociacaoAgendamentoDto = fachadaAgendamento
											.editarAgendamentoSemRestricaoAssociacaoPreCadastro(
													esperaObtido.eDescricaoServicoSocialEModuloExcessoDeFaltas(),
													new FabricaAgendamento().converterParaDTO(agendamentoObtido), tokenDto);
								}
								if (resultadoAssociacaoAgendamentoDto.sucesso() && resultadoEdicaoEsperaDto.sucesso()) {
									resultadoDto.efetuadoComSucesso(resultadoEdicaoEsperaDto.obterObjetoDtoEditado());
									logger.info(contaAcesso
											+ " efetuou Finalização de Edição de Espera e Associação de Agendamento.");
								} else {
									resultadoDto.adicionarErro(resultadoEdicaoEsperaDto.obterMensagens());
									resultadoDto.adicionarErro(resultadoAssociacaoAgendamentoDto.obterMensagens());
								}
							} else {
								resultadoDto.adicionarErro(agendamentoObtido.obterDescricaoErros());
							}
						} else {
							resultadoDto.adicionarErro(Fachada.MENSAGEM_USUARIO_JA_AGENDADO);
						}
					} else {
						resultadoDto.adicionarErro(Fachada.MENSAGEM_BLOQUEIO_POR_FALTAS_EXCESSIVAS);
					}
				} else {
					resultadoDto.adicionarErro(MENSAGEM_ESPERA_JA_FOI_AGENDADA_OU_MODULO_BLOQUEADO);
				}
			} else {
				resultadoDto.adicionarErro("Erros espera: " + esperaObtido.obterErros().toString()
						+ ", Erros agendamento: " + agendamentoObtido.obterErros());
			}
		} else {
			resultadoDto.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoDto;
	}
}
