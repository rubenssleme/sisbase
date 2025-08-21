package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

public class FabricaStatus extends FabricaBase<StatusDTO, Status> {
	public final StatusDTO converterParaDTO(Status status) {
		return status != null ? new StatusDTO(status.toString()) : null;
	}

	public final Status converterParaDominio(StatusDTO status) {
		return status != null ? Status.valueOf(Status.class, status.toString())
				: null;
	}
}
