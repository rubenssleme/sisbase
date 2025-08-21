package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Status;

public class FabricaStatus extends FabricaBase<StatusDTO, Status> {
	public final StatusDTO converterParaDTO(Status status) {
		return status != null ? new StatusDTO(status.toString(),
				new FabricaClassificacao().converterParaDTO(status
						.getClassificacao())) : null;
	}

	public final Status converterParaDominio(StatusDTO statusDto) {
		return statusDto != null ? Status.valueOf(statusDto.toString()) : null;
	}
}
