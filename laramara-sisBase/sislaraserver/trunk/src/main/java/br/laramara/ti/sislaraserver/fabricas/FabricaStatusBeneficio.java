package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusBeneficioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.StatusBeneficio;

public class FabricaStatusBeneficio extends
		FabricaBase<StatusBeneficioDTO, StatusBeneficio> {
	public final StatusBeneficioDTO converterParaDTO(StatusBeneficio status) {
		return status != null ? new StatusBeneficioDTO(status.toString())
				: null;
	}

	public final StatusBeneficio converterParaDominio(StatusBeneficioDTO status) {
		return status != null ? StatusBeneficio.valueOf(StatusBeneficio.class,
				status.toString()) : null;
	}
}
