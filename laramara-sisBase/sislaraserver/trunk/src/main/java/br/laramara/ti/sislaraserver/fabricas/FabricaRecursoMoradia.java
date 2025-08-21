package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoMoradiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoMoradia;

public class FabricaRecursoMoradia extends FabricaBase<RecursoMoradiaDTO, RecursoMoradia> {

	public final RecursoMoradiaDTO converterParaDTO(RecursoMoradia recursoMoradia) {
		return recursoMoradia != null ? new RecursoMoradiaDTO(recursoMoradia.getId(),
				recursoMoradia.getDescricao()) : null;
	}

	public final RecursoMoradia converterParaDominio(
			RecursoMoradiaDTO recursoMoradiaDto) {
		return recursoMoradiaDto != null ? new RecursoMoradia(recursoMoradiaDto.getId(),
				recursoMoradiaDto.toString()) : null;
	}
}
