package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.DescricaoRecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.DescricaoRecurso;

public class ContextoDescricaoRecurso {

	public static DescricaoRecursoDTO construirDescricaoRecursoDTO() {
		return new DescricaoRecursoDTO(new Long(13333), "1.00m");
	}

	public static DescricaoRecurso construirDescricaoRecurso() {
		return new DescricaoRecurso(new Long(13333), "1.00m");
	}
}
