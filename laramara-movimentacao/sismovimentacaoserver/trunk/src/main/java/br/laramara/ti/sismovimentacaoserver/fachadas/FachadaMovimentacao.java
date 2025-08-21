package br.laramara.ti.sismovimentacaoserver.fachadas;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoEdicaoMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemAbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemFibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemPapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemStatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemTextoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.SimNao;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.ABDB;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Fibra;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Papel;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Status;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaAbdb;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaEspecificacaoPesquisaMovimentacao;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaFibra;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaMovimentacao;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaPapel;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaSimNao;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaStatus;
import br.laramara.ti.sismovimentacaoserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sismovimentacaoserver.fachadas.operacoes.OperacaoMovimentacaoEdicao;
import br.laramara.ti.sismovimentacaoserver.repositorios.EspecificacaoPesquisaMovimentacao;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioMovimentacao;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioMovimentacaoLegado;

@Component
public class FachadaMovimentacao extends Fachada {

	@Inject
	private RepositorioMovimentacao repositorioMovimentacao;
	
	@Inject
	private RepositorioMovimentacaoLegado repositorioMovimentacaoLegado;

	public ResultadoListagemMovimentacaoDTO obterListagemMovimentacao(
			EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDto) {
		ResultadoListagemMovimentacaoDTO resultadoListagemMovimentacaoDTO = new ResultadoListagemMovimentacaoDTO();
		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = new FabricaEspecificacaoPesquisaMovimentacao()
				.converterParaDominio(especificacaoPesquisaMovimentacaoDto);
		especificacaoPesquisaMovimentacao
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (especificacaoPesquisaMovimentacao.validado()) {
			resultadoListagemMovimentacaoDTO
					.efetuadoComSucesso(new FabricaMovimentacao().converterParaDTO(repositorioMovimentacao
							.obterPor(especificacaoPesquisaMovimentacao)));
		} else {
			resultadoListagemMovimentacaoDTO
					.adicionarErro(especificacaoPesquisaMovimentacao
							.obterDescricaoErros());
		}
		return resultadoListagemMovimentacaoDTO;
	}

	public ResultadoListagemSimNaoDTO obterListagemSimNao() {
		return (ResultadoListagemSimNaoDTO) obterListagem(
				Arrays.asList(SimNao.values()), new FabricaSimNao(), "SimNao",
				new ResultadoListagemSimNaoDTO());
	}

	public ResultadoListagemFibraDTO obterListagemFibra() {
		return (ResultadoListagemFibraDTO) obterListagem(
				Arrays.asList(Fibra.values()), new FabricaFibra(), "Fibra",
				new ResultadoListagemFibraDTO());
	}

	public ResultadoListagemPapelDTO obterListagemPapel() {
		return (ResultadoListagemPapelDTO) obterListagem(
				Arrays.asList(Papel.values()), new FabricaPapel(), "Papel",
				new ResultadoListagemPapelDTO());
	}

	public ResultadoListagemAbdbDTO obterListagemAbdb() {
		return (ResultadoListagemAbdbDTO) obterListagem(
				Arrays.asList(ABDB.values()), new FabricaAbdb(), "Abdb",
				new ResultadoListagemAbdbDTO());
	}
	
	public ResultadoEdicaoMovimentacaoDTO editarMovimentacao(
			MovimentacaoDTO movimentacaoDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoMovimentacaoEdicao(
				new FabricaMovimentacao(), repositorioMovimentacao,
				movimentacaoDto);
		return (ResultadoEdicaoMovimentacaoDTO) verificarPermissaoEProcessar(
				operacao, Permissao.MOVIMENTACAO_EDICAO,
				new ResultadoEdicaoMovimentacaoDTO(), tokenDto);
	}

	private ResultadoListagemTextoDTO obterResultado(List<String> dados){
		ResultadoListagemTextoDTO resultadoListagemTextoDTO = new ResultadoListagemTextoDTO();
		resultadoListagemTextoDTO.efetuadoComSucesso(dados);
		return resultadoListagemTextoDTO;
	}
	
	public ResultadoListagemTextoDTO obterListagemCliente() {
		return obterResultado(repositorioMovimentacao.obterListagemCliente());
	}

	public ResultadoListagemTextoDTO obterListagemDescricao() {
		return obterResultado(repositorioMovimentacao.obterListagemDescricao());
	}

	public ResultadoListagemTextoDTO obterListagemBobina() {
		return obterResultado(repositorioMovimentacao.obterListagemBobina());
	}
	
	public ResultadoListagemTextoDTO obterListagemCor() {
		return obterResultado(repositorioMovimentacao.obterListagemCor());
	}

	public ResultadoListagemTextoDTO obterListagemDescricaoProduto() {
		return obterResultado(repositorioMovimentacao.obterListagemDescricaoProduto());
	}
	
	public ResultadoListagemTextoDTO obterListagemPlanaPapel() {
		return obterResultado(repositorioMovimentacao.obterListagemPlanaPapel());
	}

	public ResultadoListagemTextoDTO obterListagemTipoProva() {
		return obterResultado(repositorioMovimentacao.obterListagemTipoProva());
	}
	
	public ResultadoListagemStatusDTO obterListagemStatus() {
		return (ResultadoListagemStatusDTO) obterListagem(
				Status.obterStatusDisponiveis(), new FabricaStatus(), "Status",
				new ResultadoListagemStatusDTO());
	}

	public ResultadoEdicaoMovimentacaoDTO editarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, StatusDTO status, String data,
			TokenDTO tokenDto) {
		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDto = new ResultadoEdicaoMovimentacaoDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.MOVIMENTACAO_EDICAO)) {
			Movimentacao movimentacao = new FabricaMovimentacao()
					.converterParaDominio(movimentacaoDto,
							repositorioMovimentacao.obterPorId(movimentacaoDto
									.getId()));
			
			movimentacao.adicionarStatus(
					new FabricaStatus().converterParaDominio(status), data,
					contaAcesso);
			
			movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (movimentacao.validado()) {
				logger.info(contaAcesso
						+ " efetuou Solicitação de Edição do Status do"
						+ movimentacao);
				Movimentacao movimentacaoSalvo = repositorioMovimentacao
						.salvar(movimentacao);
				resultadoEdicaoMovimentacaoDto
						.efetuadoComSucesso(new FabricaMovimentacao()
								.converterParaDTO(movimentacaoSalvo));
			} else {
				resultadoEdicaoMovimentacaoDto.adicionarErro(movimentacao
						.obterDescricaoErros());
			}
		} else {
			resultadoEdicaoMovimentacaoDto
					.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoEdicaoMovimentacaoDto;
	}

	public ResultadoEdicaoMovimentacaoDTO avancarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, String dataHora, TokenDTO tokenDto) {
		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDto = new ResultadoEdicaoMovimentacaoDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.MOVIMENTACAO_EDICAO)) {
			Movimentacao movimentacao = new FabricaMovimentacao()
					.converterParaDominio(movimentacaoDto,
							repositorioMovimentacao.obterPorId(movimentacaoDto
									.getId()));
			if (movimentacao.estaLiberadoParaTransicao()) {
				movimentacao.avancarHistoricoStatus(dataHora, contaAcesso);
				movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();

				if (movimentacao.validado()) {
					logger.info(contaAcesso
							+ " efetuou Solicitação de Evolução de Status do"
							+ movimentacao);
					Movimentacao movimentacaoSalvo = repositorioMovimentacao
							.salvar(movimentacao);
					resultadoEdicaoMovimentacaoDto
							.efetuadoComSucesso(new FabricaMovimentacao()
									.converterParaDTO(movimentacaoSalvo));
				} else {
					resultadoEdicaoMovimentacaoDto.adicionarErro(movimentacao
							.obterDescricaoErros());
				}
			} else {
				resultadoEdicaoMovimentacaoDto
						.adicionarErro(MENSAGEM_STATUS_FINAL);
			}
		} else {
			resultadoEdicaoMovimentacaoDto
					.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoEdicaoMovimentacaoDto;
	}

	public String obterHistoricoLegado(Long id) {
		return repositorioMovimentacaoLegado.obterHistorico(id);
	}

	public ResultadoEdicaoMovimentacaoDTO inclurirMovimentacao(String data,
			TokenDTO tokenDto) {
		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDto = new ResultadoEdicaoMovimentacaoDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);

		if (contaAcesso.possuiPermissao(Permissao.MOVIMENTACAO_EDICAO)) {

			Movimentacao movimentacao = new Movimentacao();
			movimentacao.adicionarStatus(Status.ENTRADA_DO_ARQUIVO, data,
					contaAcesso);
			movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();

			if (movimentacao.validado()) {
				logger.info(contaAcesso
						+ " efetuou Solicitação de Inclusão de Movimentação"
						+ movimentacao);
				Movimentacao movimentacaoSalvo = repositorioMovimentacao
						.salvar(movimentacao);
				resultadoEdicaoMovimentacaoDto
						.efetuadoComSucesso(new FabricaMovimentacao()
								.converterParaDTO(movimentacaoSalvo));
			} else {
				resultadoEdicaoMovimentacaoDto.adicionarErro(movimentacao
						.obterDescricaoErros());
			}
		} else {
			resultadoEdicaoMovimentacaoDto
					.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}

		return resultadoEdicaoMovimentacaoDto;
	}
}
