package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoEDescricaoRecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoEDescricaoRecurso;

public class ContextoRecursoDescricaoRecurso {

	public static RecursoEDescricaoRecurso criar() {
		return new RecursoEDescricaoRecurso(ContextoRecurso.fabricarRecursoComTodosOsDados(),
				ContextoDescricaoRecurso.construirDescricaoRecurso());
	}
	
	public static RecursoEDescricaoRecurso criarSemRecursoEDescricaoRecurso() {
		return new RecursoEDescricaoRecurso(null, null);
	}

	public static RecursoEDescricaoRecursoDTO criarDto() {
		return new RecursoEDescricaoRecursoDTO(ContextoRecurso.construirRecursoDTO(),
				ContextoDescricaoRecurso.construirDescricaoRecursoDTO());
	}
	
	public static RecursoEDescricaoRecursoDTO criarAlternativoDto() {
		return new RecursoEDescricaoRecursoDTO(ContextoRecurso.construirRecursoAlternativoDTO(),
				ContextoDescricaoRecurso.construirDescricaoRecursoDTO());
	}
}
