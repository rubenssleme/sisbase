package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public class ContextoProfissional {

	public static ProfissionalDTO construirProfissionalDTO() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1000),
				"Paulo Augusto", "1222");
		return profissionalDTO;
	}
	
	public static ProfissionalDTO construirProfissionalVPereiraDTO() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(6),
				"Vera Pereira", "");
		return profissionalDTO;
	}

	public static ProfissionalDTO construirProfissionalDTOComId3000() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(3000),
				"Shivas", "");
		return profissionalDTO;
	}
	
	public static ProfissionalDTO construirProfissionalDTOComId4000() {
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(4000),
				"Adailza", "");
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
