package br.laramara.ti.sislaraserver.fachadas;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemStatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemFinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.dominio.solicitacao.Elaborador;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.StatusSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.FinalidadeRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaElaborador;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaFinalidadeRelatorio;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEfetuarCancelamentoSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEfetuarEmissaoProfissionalSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEfetuarEncaminhamentoRecepcaoSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoEfetuarEntregaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoSolicitacaoRelatorioEdicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSolicitacaoRelatorio;

@Component
public class FachadaSolicitacaoRelatorio extends Fachada {

	@Inject
	private RepositorioSolicitacaoRelatorio repositorioSolicitacaoRelatorio;
	@Inject
	private FabricaSolicitacaoRelatorio fabricaSolicitacaoRelatorio;

	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarCancelamento(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEfetuarCancelamentoSolicitacaoRelatorio(
				fabricaSolicitacaoRelatorio, repositorioSolicitacaoRelatorio,
				solicitacaoRelatorioDto);
		return (ResultadoEdicaoSolicitacaoRelatorioDTO) verificarPermissaoEProcessar(
				operacao, Permissao.SOLICITACAO_RELATORIO_EDICAO,
				new ResultadoEdicaoSolicitacaoRelatorioDTO(), tokenDto);
	}

	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarSolicitacao(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoSolicitacaoRelatorioEdicao(
				fabricaSolicitacaoRelatorio, repositorioSolicitacaoRelatorio,
				solicitacaoRelatorioDto);
		return (ResultadoEdicaoSolicitacaoRelatorioDTO) verificarPermissaoEProcessar(
				operacao, Permissao.SOLICITACAO_RELATORIO_EDICAO,
				new ResultadoEdicaoSolicitacaoRelatorioDTO(), tokenDto);
	}

	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEmissaoPorProfissional(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEfetuarEmissaoProfissionalSolicitacaoRelatorio(
				fabricaSolicitacaoRelatorio, repositorioSolicitacaoRelatorio,
				solicitacaoRelatorioDto);
		return (ResultadoEdicaoSolicitacaoRelatorioDTO) verificarPermissaoEProcessar(
				operacao, Permissao.SOLICITACAO_RELATORIO_EDICAO,
				new ResultadoEdicaoSolicitacaoRelatorioDTO(), tokenDto);
	}

	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEntrega(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEfetuarEntregaSolicitacaoRelatorio(
				fabricaSolicitacaoRelatorio, repositorioSolicitacaoRelatorio,
				solicitacaoRelatorioDto);
		return (ResultadoEdicaoSolicitacaoRelatorioDTO) verificarPermissaoEProcessar(
				operacao, Permissao.SOLICITACAO_RELATORIO_EDICAO,
				new ResultadoEdicaoSolicitacaoRelatorioDTO(), tokenDto);
	}

	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEncaminhamentoRecepcao(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoEfetuarEncaminhamentoRecepcaoSolicitacaoRelatorio(
				fabricaSolicitacaoRelatorio, repositorioSolicitacaoRelatorio,
				solicitacaoRelatorioDto);
		return (ResultadoEdicaoSolicitacaoRelatorioDTO) verificarPermissaoEProcessar(
				operacao, Permissao.SOLICITACAO_RELATORIO_EDICAO,
				new ResultadoEdicaoSolicitacaoRelatorioDTO(), tokenDto);
	}

	public ResultadoListagemSolicitacaoRelatorioDTO pesquisarSolicitacaoRelatorioPor(
			EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao) {
		ResultadoListagemSolicitacaoRelatorioDTO resultadoDto = new ResultadoListagemSolicitacaoRelatorioDTO();

		EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio = new FabricaEspecificacaoPesquisaSolicitacaoRelatorio()
				.converterParaDominio((EspecificacaoPesquisaSolicitacaoRelatorioDTO) especificacao);
		especificacaoPesquisaSolicitacaoRelatorio
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (especificacaoPesquisaSolicitacaoRelatorio.validado()) {
			resultadoDto = (ResultadoListagemSolicitacaoRelatorioDTO) obterListagem(
					repositorioSolicitacaoRelatorio
							.obterPor(especificacaoPesquisaSolicitacaoRelatorio),
					new FabricaSolicitacaoRelatorio(),
					"Solicitacao de Relatorio",
					new ResultadoListagemSolicitacaoRelatorioDTO());
		} else {
			resultadoDto
					.adicionarErro(especificacaoPesquisaSolicitacaoRelatorio
							.obterDescricaoErros());
		}
		return resultadoDto;
	}

	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusEntrega() {
		return (ResultadoListagemStatusSolicitacaoRelatorioDTO) obterListagem(
				StatusSolicitacaoRelatorio.obterStatusEntrega(),
				new FabricaStatusSolicitacaoRelatorio(),
				"Status de Entrega de Solicitacao de Relatorio",
				new ResultadoListagemStatusSolicitacaoRelatorioDTO());
	}

	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusSolicitacaoRelatorio() {
		return (ResultadoListagemStatusSolicitacaoRelatorioDTO) obterListagem(
				Arrays.asList(StatusSolicitacaoRelatorio.values()),
				new FabricaStatusSolicitacaoRelatorio(),
				"Status de Solicitacao de Relatorio",
				new ResultadoListagemStatusSolicitacaoRelatorioDTO());
	}

	public ResultadoListagemFinalidadeRelatorioDTO obterListagemFinalidadeRelatorio() {
		return (ResultadoListagemFinalidadeRelatorioDTO) obterListagem(
				Arrays.asList(FinalidadeRelatorio.values()),
				new FabricaFinalidadeRelatorio(), "Finalidade de Relatorio",
				new ResultadoListagemFinalidadeRelatorioDTO());
	}

	public ResultadoListagemElaboradorDTO obterListagemElaborador() {
		return (ResultadoListagemElaboradorDTO) obterListagem(
				Arrays.asList(Elaborador.values()), new FabricaElaborador(),
				"Elaboradores", new ResultadoListagemElaboradorDTO());
	}
}
