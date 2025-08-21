package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ExpectativaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;

public class ContextoExpectativa {

	public static ExpectativaDTO construirExpectativaDTO() {
		return new ExpectativaDTO(new Long(1000), "Texto da expectativa");
	}

	public static Expectativa fabricarExpectativaComTodosOsDados() {
		return new Expectativa(new Long(1000), "Texot de expectativa");
	}

}
