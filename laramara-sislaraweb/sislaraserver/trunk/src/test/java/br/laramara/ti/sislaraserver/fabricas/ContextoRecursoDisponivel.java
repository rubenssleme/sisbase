package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoDisponivel;

public class ContextoRecursoDisponivel {
	public static RecursoDisponivelDTO fabricarComTodosOsDadosDTO() {
		Long id = new Long(12345);
		String quantidade = "5";
		String valor = "150,00";
		RecursoDTO recursoDTO = ContextoRecurso.construirRecursoDTO();
		RecursoDisponivelDTO recursoDisponivelDTO = new RecursoDisponivelDTO();
		recursoDisponivelDTO.setId(id);
		recursoDisponivelDTO.setRecursoDto(recursoDTO);
		recursoDisponivelDTO.setValorUnitario(valor);
		recursoDisponivelDTO.setQuantidade(quantidade);
		return recursoDisponivelDTO;
	}

	public static RecursoDisponivel fabricarComTodosOsDados() {
		RecursoDisponivel recursoDisponivel = new RecursoDisponivel();
		recursoDisponivel.setId(new Long(12345));
		recursoDisponivel.setRecurso(ContextoRecurso.fabricarRecursoComTodosOsDados());
		recursoDisponivel.setQuantidade("2");
		recursoDisponivel.setValorUnitario("150,00");
		return recursoDisponivel;
	}
}
