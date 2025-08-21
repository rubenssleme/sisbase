package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;

public class ContextoLoteRecurso {

	public static LoteRecursoDTO fabricarComTodosOsDadosDTO() {
		Long id = new Long(12345);
		String quantidade = "5";
		String valor = "2500,00";
		RecursoDTO recursoDTO = ContextoRecurso.construirRecursoDTO();
		LoteRecursoDTO doacaoRecursoDTO = new LoteRecursoDTO(id, recursoDTO,
				quantidade, valor);
		return doacaoRecursoDTO;
	}

	public static LoteRecurso fabricarComTodosOsDados() {
		LoteRecurso loteRecurso = new LoteRecurso();
		loteRecurso.setId(new Long(12345));
		loteRecurso.setRecurso(ContextoRecurso.fabricarRecursoComTodosOsDados());
		loteRecurso.setQuantidade("2");
		loteRecurso.setValor("5000,00");
		return loteRecurso;
	}
}
