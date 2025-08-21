package br.laramara.ti.sislaraserver.fachadas;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemMotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoCaptacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoEdicaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemNomeDocumentoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemStatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoProrrogacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.doacao.Demandador;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoCaptacaoDemanda;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoGeracaoDemanda;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;
import br.laramara.ti.sislaraserver.dominio.doacao.NomeDocumento;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusDemanda;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaDemanda;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoCaptacaoDemanda;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoGeracaoDemanda;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoPesquisaDemanda;
import br.laramara.ti.sislaraserver.fabricas.FabricaMotivoCancelamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaNomeDocumento;
import br.laramara.ti.sislaraserver.fabricas.FabricaProjeto;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusDemanda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDemanda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMotivoCancelamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProjeto;

@Component
public class FachadaDemanda extends Fachada {

	@Inject
	private RepositorioDemanda repositorioDemanda;
	@Inject
	private RepositorioProjeto repositorioProjeto;
	@Inject
	private Demandador demandador;
	@Inject
	private RepositorioArquivo repositorioArquivo;
	@Inject
	private RepositorioMotivoCancelamento repositorioMotivoCancelamento;

	public ResultadoListagemDemandaDTO obterListagemDemanda(
			EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDto) {
		ResultadoListagemDemandaDTO resultadoDto = new ResultadoListagemDemandaDTO();	

		EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new FabricaEspecificacaoPesquisaDemanda()
				.converterParaDominio((EspecificacaoPesquisaDemandaDTO) especificacaoPesquisaDemandaDto);
		especificacaoPesquisaDemanda
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (especificacaoPesquisaDemanda.validado()) {
			resultadoDto = (ResultadoListagemDemandaDTO) obterListagem(
					repositorioDemanda.obterPor(especificacaoPesquisaDemanda),
					new FabricaDemanda(), "Demanda",
					new ResultadoListagemDemandaDTO());
		} else {
			resultadoDto.adicionarErro(especificacaoPesquisaDemanda
					.obterDescricaoErros());
		}
		return resultadoDto;
	}

	public ResultadoListagemProjetoDTO obterListagemProjetoAtivo() {
		return (ResultadoListagemProjetoDTO) obterListagem(
				repositorioProjeto.obterAtivos(), new FabricaProjeto(),
				"Projetos", new ResultadoListagemProjetoDTO());
	}

	public ResultadoGeracaoDemandaDTO gerarDemanda(EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO,
			TokenDTO tokenDto) {
		ResultadoGeracaoDemandaDTO resultado = new ResultadoGeracaoDemandaDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		try {
			if (contaAcesso.possuiPermissao(Permissao.DEMANDA_EDICAO)) {
				EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda = new FabricaEspecificacaoGeracaoDemanda()
						.converterParaDominio(especificacaoGeracaoDemandaDTO);
				especificacaoGeracaoDemanda.atribuirContaAcessoResponsavel(contaAcesso);
				especificacaoGeracaoDemanda.validarObrigatoriedadeETamanhoMaximoDeDados();
				if (especificacaoGeracaoDemanda.validado()) {
					List<Demanda> demandas = demandador.gerarDemandas(especificacaoGeracaoDemanda, contaAcesso);
					logger.info(contaAcesso + " efetuou Geração de Demanda para " + especificacaoGeracaoDemanda
							+ " com sucesso.");
					resultado.efetuadoComSucesso(new FabricaDemanda().converterParaDTO(demandas));
				} else {
					resultado.adicionarErro(especificacaoGeracaoDemanda.obterDescricaoErros());
				}
			} else {
				resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
			}
		} catch (Exception e) {
			resultado.adicionarErro(e.getMessage());
		}
		return resultado;
	}

	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemandaLimitada() {
		return (ResultadoListagemStatusDemandaDTO) obterListagem(
				StatusDemanda.obterStatusLimitado(),
				new FabricaStatusDemanda(), "Status de Demanda Limitado",
				new ResultadoListagemStatusDemandaDTO());
	}
	
	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemanda() {
		return (ResultadoListagemStatusDemandaDTO) obterListagem(
				StatusDemanda.obterStatus(),
				new FabricaStatusDemanda(), "Status de Demanda",
				new ResultadoListagemStatusDemandaDTO());
	}
	
	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoDemanda() {
		return (ResultadoListagemMotivoCancelamentoDTO) obterListagem(
				repositorioMotivoCancelamento.obterTodosDemanda(),
				new FabricaMotivoCancelamento(), "Motivos de Cancelamento",
				new ResultadoListagemMotivoCancelamentoDTO());
	}
	
	public ResultadoListagemNomeDocumentoDTO obterListagemNomeDocumento() {
		return (ResultadoListagemNomeDocumentoDTO) obterListagem(Arrays.asList(NomeDocumento.values()),
				new FabricaNomeDocumento(), "Nome dos documentos", new ResultadoListagemNomeDocumentoDTO());
	}
	
	public ResultadoEdicaoDemandaDTO mudarStatusDemanda(DemandaDTO demandaDto, StatusDemandaDTO statusDemandaDto, MotivoCancelamentoDTO motivoCancelamentoDTO,
			String obs, TokenDTO tokenDto) {
		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDTO = new ResultadoEdicaoDemandaDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);

		Demanda demanda = new FabricaDemanda().converterParaDominio(demandaDto,
				repositorioDemanda.obterPorId(demandaDto.getId()));
		StatusDemanda novoStatusDemanda = new FabricaStatusDemanda().converterParaDominio(statusDemandaDto);
		
		if (possuiDemandaReservadaENovoStatusDisponivelEPossuiPermissaoParaDisponibilizacao(contaAcesso, demanda,
				novoStatusDemanda)
				|| possuiDemandaDisponivelENovoStatusEntregueEPossuiPermissaoParaEntregar(contaAcesso, demanda,
						novoStatusDemanda)
				|| possuiNovoStatusCanceladaEPossuiPermissaoParaCancelamento(contaAcesso, demanda, novoStatusDemanda)) {
			alterarStatusESalvar(resultadoEdicaoDemandaDTO, novoStatusDemanda, motivoCancelamentoDTO, obs, contaAcesso,
					demanda);
		} else {
			resultadoEdicaoDemandaDTO.adicionarErro(MENSAGEM_NAO_E_POSSIVEL_REALIZAR_MUDANCA_DE_STATUS);
		}
		return resultadoEdicaoDemandaDTO;
	}

	private boolean possuiNovoStatusCanceladaEPossuiPermissaoParaCancelamento(ContaAcesso contaAcesso, Demanda demanda,
			StatusDemanda novoStatusDemanda) {
		return !demanda.estaCancelada() && novoStatusDemanda.equals(StatusDemanda.CANCELADA)
				&& contaAcesso.possuiPermissao(Permissao.DEMANDA_CANCELAR);
	}

	private boolean possuiDemandaDisponivelENovoStatusEntregueEPossuiPermissaoParaEntregar(ContaAcesso contaAcesso,
			Demanda demanda, StatusDemanda novoStatusDemanda) {
		return demanda.estaDisponivel() && novoStatusDemanda.equals(StatusDemanda.ENTREGUE)
				&& contaAcesso.possuiPermissao(Permissao.DEMANDA_ENTREGAR);
	}

	private boolean possuiDemandaReservadaENovoStatusDisponivelEPossuiPermissaoParaDisponibilizacao(
			ContaAcesso contaAcesso, Demanda demanda, StatusDemanda novoStatusDemanda) {
		return demanda.estaReservado() && novoStatusDemanda.equals(StatusDemanda.DISPONIVEL)
				&& contaAcesso.possuiPermissao(Permissao.DEMANDA_DISPONIBILIZAR);
	}

	private void alterarStatusESalvar(ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDTO,
			StatusDemanda novoStatusDemanda, MotivoCancelamentoDTO motivoCancelamentoDTO, String obs,
			ContaAcesso contaAcesso, Demanda demanda) {
		demanda.setMotivoCancelamento(new FabricaMotivoCancelamento().converterParaDominio(motivoCancelamentoDTO));
		demanda.setObs(obs);
		demanda.adicionarStatusDemanda(novoStatusDemanda);
		demanda.setContaAcessoResponsavelPorOperacao(contaAcesso);
		demanda.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (demanda.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Mudanca de Status da " + demanda);
			Demanda demandaSalvo = repositorioDemanda.salvar(demanda);
			resultadoEdicaoDemandaDTO.efetuadoComSucesso(new FabricaDemanda().converterParaDTO(demandaSalvo));
		} else {
			resultadoEdicaoDemandaDTO.adicionarErro(demanda.obterDescricaoErros());
		}
	}

	public ArquivoDTO obterArquivoDocumentoSolicitacaoDoacao(DemandaDTO demandaDto, ArquivoDTO arquivoDTO) {
		return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoDocumentoSolicitacaoDoacao(
				new FabricaDemanda().converterParaDominio(demandaDto),
				new FabricaArquivo().converterParaDominio(arquivoDTO)));
	}

	public ResultadoProrrogacaoCartelaDeSelosDTO prorrogar(DemandaDTO demandaDto, TokenDTO tokenDto) {
		ResultadoProrrogacaoCartelaDeSelosDTO resultado = new ResultadoProrrogacaoCartelaDeSelosDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.DEMANDA_PRORROGAR)) {
			Demanda demanda = repositorioDemanda.obterPorId(demandaDto.getId());
			demanda.prorrogar(contaAcesso);
			if (demanda.validado()) {
				Demanda demandaSalva = repositorioDemanda.salvar(demanda);
				logger.info(contaAcesso + " efetuou prorrogação de " + demandaSalva + " com sucesso.");
				resultado.efetuadoComSucesso(new FabricaDemanda().converterParaDTO(demandaSalva));
			} else {
				resultado.adicionarErro(demanda.obterDescricaoErros());
			}
		} else {
			resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultado;
	}

	public ResultadoCaptacaoCartelaDeSelosDTO captar(EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDto,
			TokenDTO tokenDto) {
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDTO = new ResultadoCaptacaoCartelaDeSelosDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.DEMANDA_CAPTAR)) {
			EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda = new FabricaEspecificacaoCaptacaoDemanda()
					.converterParaDominio(especificacaoCaptacaoDemandaDto);
			especificacaoCaptacaoDemanda.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (especificacaoCaptacaoDemanda.validado()) {
				BigDecimal valorTotalCaptadoNoRecibo = obterValorTotalCaptadoNoRecibo(especificacaoCaptacaoDemanda);
				if (DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(
						especificacaoCaptacaoDemanda.getRecibo().getValorTotalRecibo(), valorTotalCaptadoNoRecibo)) {
					Demanda demanda = repositorioDemanda.obterPorId(especificacaoCaptacaoDemanda.getDemanda().getId());
					demanda.adicionarCaptacaoEmDemandasComStatusAguardandoOuProrrogada(especificacaoCaptacaoDemanda, contaAcesso);
					demanda.validarObrigatoriedadeETamanhoMaximoDeDados();
					if (demanda.validado()) {
						Demanda demandaSalva = repositorioDemanda.salvar(demanda);
						logger.info(
								contaAcesso + " efetuou captação de " + especificacaoCaptacaoDemanda + " com sucesso.");
						resultadoCaptacaoCartelaDeSelosDTO
								.efetuadoComSucesso(new FabricaDemanda().converterParaDTO(demandaSalva));
					}else{
						resultadoCaptacaoCartelaDeSelosDTO.adicionarErro(demanda.obterDescricaoErros());
					}
				}else{
					resultadoCaptacaoCartelaDeSelosDTO.adicionarErro("Valor total do recibo insuficiente para captação.");
				}
			} else {
				resultadoCaptacaoCartelaDeSelosDTO.adicionarErro(especificacaoCaptacaoDemanda.obterDescricaoErros());
			}
		} else {
			resultadoCaptacaoCartelaDeSelosDTO.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoCaptacaoCartelaDeSelosDTO;
	}

	private BigDecimal obterValorTotalCaptadoNoRecibo(EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda) {
		EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
		especificacaoPesquisaDemanda
				.setNumeroRecibo(especificacaoCaptacaoDemanda.getRecibo().getId().toString());
		List<Demanda> demandas = repositorioDemanda.obterPor(especificacaoPesquisaDemanda);
		return demandas.stream()
				.map(demandaASomar -> demandaASomar
						.obterValorTotalCaptadoRecibo(especificacaoCaptacaoDemanda.getRecibo().getId()))
				.reduce(BigDecimal.ZERO, BigDecimal::add).add(especificacaoCaptacaoDemanda.getValor());
	}
}
