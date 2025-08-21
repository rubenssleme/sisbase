package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.contribuicao.StatusContribuinteDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.StatusContribuinte;

public class FabricaStatusContribuinte extends FabricaBase<StatusContribuinteDTO, StatusContribuinte> {
	public final StatusContribuinteDTO converterParaDTO(StatusContribuinte status) {
		return status != null ? new StatusContribuinteDTO(status.toString()) : null;
	}

	public final StatusContribuinte converterParaDominio(StatusContribuinteDTO status) {
		return status != null ? StatusContribuinte.valueOf(StatusContribuinte.class, status.toString()) : null;
	}
}
