package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

public class ContextoProfissional {

	public static ProfissionalDTO construirProfissionalDTO() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1000),
				"Paulo Augusto", "1222");
		return profissionalDTO;
	}
	
	public static ProfissionalDTO construirProfissionalDTOAlternativo() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(2000),
				"Paulo Augusto", "1222");
		return profissionalDTO;
	}

	public static Profissional fabricarComTodosOsDados() {
		return new Profissional(new Long(1000), "Paulo Augusto", "12333");
	}
}
