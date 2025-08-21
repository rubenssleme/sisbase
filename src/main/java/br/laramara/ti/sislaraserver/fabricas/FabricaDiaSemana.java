package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;

public class FabricaDiaSemana extends FabricaBase<DiaSemanaDTO, DiaSemana> {
	public final DiaSemanaDTO converterParaDTO(DiaSemana diaSemana) {
		return diaSemana != null ? new DiaSemanaDTO(diaSemana.toString())
				: null;
	}

	public final DiaSemana converterParaDominio(
			DiaSemanaDTO diaSemana) {
		return diaSemana != null ? DiaSemana.valueOf(DiaSemana.class,
				diaSemana.toString()) : null;
	}
}
