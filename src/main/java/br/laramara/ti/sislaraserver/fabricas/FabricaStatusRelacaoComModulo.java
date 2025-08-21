package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

public class FabricaStatusRelacaoComModulo extends
		FabricaBase<StatusRelacaoComModuloDTO, StatusRelacaoComModulo> {
	public final StatusRelacaoComModuloDTO converterParaDTO(
			StatusRelacaoComModulo status) {
		return status != null ? new StatusRelacaoComModuloDTO(status.toString()) : null;
	}

	public final StatusRelacaoComModulo converterParaDominio(
			StatusRelacaoComModuloDTO status) {
		return status != null ? StatusRelacaoComModulo.valueOf(
				StatusRelacaoComModulo.class, status.toString()) : null;
	}
}
