package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.List;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSolicitacaoRelatorio;

public class OperacaoSolicitacaoRelatorioEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoSolicitacaoRelatorioEdicao.class);

	private FabricaSolicitacaoRelatorio fabricaSolicitacaoRelatorio;
	private RepositorioSolicitacaoRelatorio repositorioSolicitacaoRelatorio;
	private SolicitacaoRelatorioDTO solicitacaoRelatorioDto;

	public OperacaoSolicitacaoRelatorioEdicao(
			FabricaSolicitacaoRelatorio fabricaSolicitacaoRelatorio,
			RepositorioSolicitacaoRelatorio repositorioSolicitacaoRelatorio,
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto) {
		this.fabricaSolicitacaoRelatorio = fabricaSolicitacaoRelatorio;
		this.repositorioSolicitacaoRelatorio = repositorioSolicitacaoRelatorio;
		this.solicitacaoRelatorioDto = solicitacaoRelatorioDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		SolicitacaoRelatorio solicitacaoRelatorio = fabricaSolicitacaoRelatorio
				.converterParaDominio(solicitacaoRelatorioDto,
						repositorioSolicitacaoRelatorio
								.obterPorId(solicitacaoRelatorioDto.getId()));
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoSolicitacao(contaAcesso);
		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (solicitacaoRelatorio.validado()) {
			if (podeRealizarNovaSolicitacaoDeRelatorio(solicitacaoRelatorio)) {
				logger.info(contaAcesso + " efetuou Solicitação de Edição do " + solicitacaoRelatorio);
				SolicitacaoRelatorio solicitacaoRelatorioSalvo = repositorioSolicitacaoRelatorio
						.salvar(solicitacaoRelatorio);
				resultadoDto
						.efetuadoComSucesso(fabricaSolicitacaoRelatorio.converterParaDTO(solicitacaoRelatorioSalvo));

			} else {
				resultadoDto.adicionarErro(
						"Não é possível realizar a operação. Já foi solicitado relatório nos últimos 6 meses.");
			}
		} else {
			resultadoDto.adicionarErro(solicitacaoRelatorio.obterDescricaoErros());
		}
		return resultadoDto;
	}

	private boolean podeRealizarNovaSolicitacaoDeRelatorio(SolicitacaoRelatorio solicitacaoRelatorio) {
		EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio = new EspecificacaoPesquisaSolicitacaoRelatorio();
		especificacaoPesquisaSolicitacaoRelatorio.setProntuario(solicitacaoRelatorio.getUsuario().getId().toString());
		List<SolicitacaoRelatorio> solicitacoesExistentes = repositorioSolicitacaoRelatorio
				.obterPor(especificacaoPesquisaSolicitacaoRelatorio);
		boolean possuiSolicitacoesNaoCanceladasNaoINSSNaoElaboradaOrtopticaComDataSolicitacaoMenorQueSeisMeses = solicitacoesExistentes
				.stream().anyMatch(solicitacaoExistente -> !solicitacaoExistente.estaCancelado() && !solicitacaoExistente.INSS()
						&& solicitacaoExistente.possuiDataSolicitacaoMenorQueSeisMeses() && !solicitacaoExistente.elaboradoPelaOrtoptica());
		boolean solicitacaoNaoCanceladaDoINSS = solicitacaoRelatorio.INSS() && !solicitacaoRelatorio.estaCancelado();

		return !possuiSolicitacoesNaoCanceladasNaoINSSNaoElaboradaOrtopticaComDataSolicitacaoMenorQueSeisMeses
				|| solicitacaoNaoCanceladaDoINSS || solicitacaoRelatorio.elaboradoPelaOrtoptica();
	}
}
