package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.StatusEsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;

public class FabricaStatusEspera extends FabricaBase<StatusEsperaDTO, StatusEspera> {
	public final StatusEsperaDTO converterParaDTO(
			StatusEspera status) {
		return status != null ? new StatusEsperaDTO(status.toString()) : null;
	}

	public final StatusEspera converterParaDominio(
			StatusEsperaDTO status) {
		return status != null ? StatusEspera.valueOf(StatusEspera.class,
				status.toString()) : null;
	}
}
