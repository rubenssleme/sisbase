package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Genero;

public class FabricaGenero extends FabricaBase<GeneroDTO, Genero> {
	public final GeneroDTO converterParaDTO(Genero genero) {
		return genero != null ? new GeneroDTO(genero.toString()) : null;
	}

	public final Genero converterParaDominio(GeneroDTO genero) {
		return genero != null ? Genero.valueOf(Genero.class, genero.toString())
				: null;
	}
}
