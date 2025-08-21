package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;

public class FabricaEspecificacaoPesquisaSolicitacaoRelatorio
		extends
		FabricaBase<EspecificacaoPesquisaSolicitacaoRelatorioDTO, EspecificacaoPesquisaSolicitacaoRelatorio> {

	@Override
	public EspecificacaoPesquisaSolicitacaoRelatorioDTO converterParaDTO(
			EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaSolicitacaoRelatorio converterParaDominio(
			EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacaoPesquisaSolicitacaoRelatorioDto) {
		EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio = new EspecificacaoPesquisaSolicitacaoRelatorio();
		especificacaoPesquisaSolicitacaoRelatorio
				.setProntuario(especificacaoPesquisaSolicitacaoRelatorioDto
						.getProntuario());
		especificacaoPesquisaSolicitacaoRelatorio
				.setProtocolo(especificacaoPesquisaSolicitacaoRelatorioDto
						.getProtocolo());
		especificacaoPesquisaSolicitacaoRelatorio
				.setStatus(new FabricaStatusSolicitacaoRelatorio()
						.converterParaDominio(especificacaoPesquisaSolicitacaoRelatorioDto
								.getStatusSolicitacaoRelatorio()));
		return especificacaoPesquisaSolicitacaoRelatorio;
	}
}
