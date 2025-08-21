package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;

public class FabricaEpocaIncidencia extends
		FabricaBase<EpocaIncidenciaDTO, EpocaIncidencia> {
	public final EpocaIncidenciaDTO converterParaDTO(
			EpocaIncidencia epocaIncidencia) {
		return epocaIncidencia != null ? new EpocaIncidenciaDTO(
				epocaIncidencia.toString()) : null;
	}

	public final EpocaIncidencia converterParaDominio(
			EpocaIncidenciaDTO epocaIncidencia) {
		return epocaIncidencia != null ? EpocaIncidencia.valueOf(
				EpocaIncidencia.class, epocaIncidencia.toString()) : null;
	}
}
