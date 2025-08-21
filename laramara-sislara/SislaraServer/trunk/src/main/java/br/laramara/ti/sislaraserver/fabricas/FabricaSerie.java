package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaraserver.dominio.escola.Serie;

public class FabricaSerie extends FabricaBase<SerieDTO, Serie> {
	public final SerieDTO converterParaDTO(Serie serie) {
		return serie != null ? new SerieDTO(serie.getId(), serie.getDescricao()) : null;
	}

	public final Serie converterParaDominio(SerieDTO serieDto) {
		return serieDto != null ? new Serie(serieDto.getId(),
				serieDto.toString()) : null;
	}
}
