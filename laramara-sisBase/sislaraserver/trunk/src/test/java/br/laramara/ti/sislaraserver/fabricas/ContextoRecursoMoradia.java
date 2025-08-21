package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoMoradiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoMoradia;

public class ContextoRecursoMoradia {
	public static RecursoMoradiaDTO construirRecursoMoradiaDTO() {
		return new RecursoMoradiaDTO(new Long(1000), "Hospital");
	}

	public static RecursoMoradia fabricarRecursoMoradiaComTodosOsDados() {
		return new RecursoMoradia(new Long(1000), "Hospital");
	}
}
