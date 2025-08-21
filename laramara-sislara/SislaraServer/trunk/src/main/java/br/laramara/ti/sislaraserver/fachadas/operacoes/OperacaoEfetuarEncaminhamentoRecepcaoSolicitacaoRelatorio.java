package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.FabricaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSolicitacaoRelatorio;

public class OperacaoEfetuarEncaminhamentoRecepcaoSolicitacaoRelatorio
		implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoEfetuarEncaminhamentoRecepcaoSolicitacaoRelatorio.class);

	private FabricaSolicitacaoRelatorio fabricaSolicitacaoRelatorio;
	private RepositorioSolicitacaoRelatorio repositorioSolicitacaoRelatorio;
	private SolicitacaoRelatorioDTO solicitacaoRelatorioDto;

	public OperacaoEfetuarEncaminhamentoRecepcaoSolicitacaoRelatorio(
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
				.setContaAcessoResponsavelPorOperacaoEncaminhamentoRecepcao(contaAcesso);

		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (solicitacaoRelatorio.validado()) {
			logger.info(contaAcesso
					+ " efetuou Encaminhamento pela Recepção do "
					+ solicitacaoRelatorio);
			SolicitacaoRelatorio solicitacaoRelatorioSalvo = repositorioSolicitacaoRelatorio
					.salvar(solicitacaoRelatorio);
			resultadoDto.efetuadoComSucesso(fabricaSolicitacaoRelatorio
					.converterParaDTO(solicitacaoRelatorioSalvo));
		} else {
			resultadoDto.adicionarErro(solicitacaoRelatorio
					.obterDescricaoErros());
		}
		return resultadoDto;
	}

}
