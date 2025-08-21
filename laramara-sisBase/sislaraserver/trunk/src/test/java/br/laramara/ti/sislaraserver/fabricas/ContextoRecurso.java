package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class ContextoRecurso {
	public static RecursoDTO construirRecursoDTO() {
		RecursoDTO recursoDTO = new RecursoDTO(
				new Long(12222), "Maquina Braille Nacional", true, "2500,00");
		return recursoDTO;
	}

	public static Recurso fabricarRecursoComTodosOsDados() {
		Recurso recurso = new Recurso(new Long(13333), "Bengala", false, "80,00");
		return recurso;
	}
	
	public static Recurso fabricarRecursoAlternativoComTodosOsDados() {
		Recurso recurso = new Recurso(new Long(12222), "Maquina Braille Nacional", true, "2500,00");
		return recurso;
	}

	public static RecursoDTO construirRecursoAlternativoDTO() {
		RecursoDTO recursoDTO = new RecursoDTO(
				new Long(13333), "BEngala", false, "80,00");
		return recursoDTO;
	}
}
