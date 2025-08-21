package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.StatusSolicitacaoRelatorio;

public class FabricaStatusSolicitacaoRelatorio extends
		FabricaBase<StatusSolicitacaoRelatorioDTO, StatusSolicitacaoRelatorio> {
	public final StatusSolicitacaoRelatorioDTO converterParaDTO(
			StatusSolicitacaoRelatorio status) {
		return status != null ? new StatusSolicitacaoRelatorioDTO(
				status.toString()) : null;
	}

	public final StatusSolicitacaoRelatorio converterParaDominio(
			StatusSolicitacaoRelatorioDTO status) {
		return status != null ? StatusSolicitacaoRelatorio.valueOf(
				StatusSolicitacaoRelatorio.class, status.toString()) : null;
	}
}
