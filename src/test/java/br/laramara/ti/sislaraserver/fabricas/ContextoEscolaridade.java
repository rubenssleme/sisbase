package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;

import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;

public class ContextoEscolaridade {

	public static EscolaridadeDTO construirEscolaridadeDto() {
		return new EscolaridadeDTO(new Long(1), "SUPERIOR",
				new ArrayList<SerieDTO>());
	}

	public static Escolaridade fabricarEscolaridadeComTodosOsDados() {
		return new Escolaridade(new Long(1), "SUPERIOR");
	}
}
