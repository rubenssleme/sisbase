package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusDemanda;

public class FabricaStatusDemanda extends
		FabricaBase<StatusDemandaDTO, StatusDemanda> {
	public final StatusDemandaDTO converterParaDTO(
			StatusDemanda status) {
		return status != null ? new StatusDemandaDTO(status.toString())
				: null;
	}

	public final StatusDemanda converterParaDominio(
			StatusDemandaDTO status) {
		return status != null ? StatusDemanda.valueOf(
				StatusDemanda.class, status.toString()) : null;
	}
}
