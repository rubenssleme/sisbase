package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;

public class FabricaDreCefai extends FabricaBase<DreCefaiDTO, DreCefai> {

	public final DreCefaiDTO converterParaDTO(DreCefai dreCefai) {
		return dreCefai != null ? new DreCefaiDTO(dreCefai.getId(),
				dreCefai.getNome()) : null;
	}

	public final DreCefai converterParaDominio(
			DreCefaiDTO dreCefaiDto) {
		return dreCefaiDto != null ? new DreCefai(dreCefaiDto.getId(),
				dreCefaiDto.toString()) : null;
	}
}
