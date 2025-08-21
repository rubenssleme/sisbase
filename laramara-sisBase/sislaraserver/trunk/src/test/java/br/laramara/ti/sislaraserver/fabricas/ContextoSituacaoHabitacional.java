package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoHabitacionalDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;

public class ContextoSituacaoHabitacional {

	public static SituacaoHabitacionalDTO construirSituacaoHabitacionalDto() {
		return new SituacaoHabitacionalDTO(new Long(1000), "TESTE");
	}

	public static SituacaoHabitacional fabricarSituacaoHabitacional() {
		return new SituacaoHabitacional(new Long(1000), "TESTE2");
	}
}
