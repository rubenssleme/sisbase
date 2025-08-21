package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class ContextoRecurso {
	public static RecursoDTO construirRecursoDTO() {
		RecursoDTO recursoDTO = new RecursoDTO(
				new Long(3), "Maquina Braille Nacional", true, true, "2500,00", Arrays.asList(ContextoDescricaoRecurso.construirDescricaoRecursoDTO()));
		return recursoDTO;
	}

	public static Recurso fabricarRecursoComTodosOsDados() {
		Recurso recurso = new Recurso(new Long(12), "Bengala", false, true,"80,00");
		return recurso;
	}
	
	public static Recurso fabricarRecursoAlternativoComTodosOsDados() {
		Recurso recurso = new Recurso(new Long(3), "Maquina Braille Nacional", true, true, "2500,00");
		return recurso;
	}

	public static RecursoDTO construirRecursoAlternativoDTO() {
		RecursoDTO recursoDTO = new RecursoDTO(
				new Long(12), "BEngala", false, true, "80,00", Arrays.asList(ContextoDescricaoRecurso.construirDescricaoRecursoDTO()));
		return recursoDTO;
	}
}
