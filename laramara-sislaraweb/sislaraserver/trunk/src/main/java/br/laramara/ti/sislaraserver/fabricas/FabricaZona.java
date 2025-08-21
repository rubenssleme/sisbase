package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;

public class FabricaZona extends FabricaBase<ZonaDTO, Zona>{
	public final ZonaDTO converterParaDTO(Zona zona) {
		return zona != null ? new ZonaDTO(zona.toString()) : null;
	}

	public final Zona converterParaDominio(ZonaDTO zonaDto) {
		return zonaDto != null ? Zona.valueOf(Zona.class, zonaDto.toString()) : null;
	}
}
