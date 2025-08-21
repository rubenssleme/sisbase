package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;

public class FabricaFrequencia extends FabricaBase<FrequenciaDTO, Frequencia> {

	public final FrequenciaDTO converterParaDTO(
			Frequencia frequencia) {
		return frequencia != null ? new FrequenciaDTO(frequencia.toString())
				: null;
	}

	public final Frequencia converterParaDominio(
			FrequenciaDTO frequencia) {
		return frequencia != null ? Frequencia.valueOf(Frequencia.class,
				frequencia.toString()) : null;
	}
}
