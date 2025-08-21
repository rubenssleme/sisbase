package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoHabitacionalDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;

public class FabricaSituacaoHabitacional extends FabricaBase<SituacaoHabitacionalDTO, SituacaoHabitacional> {

	public final SituacaoHabitacionalDTO converterParaDTO(SituacaoHabitacional situacaoHabitacional) {
		return situacaoHabitacional != null
				? new SituacaoHabitacionalDTO(situacaoHabitacional.getId(), situacaoHabitacional.getDescricao()) : null;
	}

	public final SituacaoHabitacional converterParaDominio(SituacaoHabitacionalDTO situacaoHabitacionalDto) {
		return situacaoHabitacionalDto != null
				? new SituacaoHabitacional(situacaoHabitacionalDto.getId(), situacaoHabitacionalDto.toString()) : null;
	}
}
