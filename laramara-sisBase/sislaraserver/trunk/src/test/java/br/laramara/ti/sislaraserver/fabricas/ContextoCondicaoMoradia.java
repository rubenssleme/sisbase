package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CondicaoMoradiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;

public class ContextoCondicaoMoradia {

	public static CondicaoMoradiaDTO contruirCondicaoMoradiaDto() {
		return new CondicaoMoradiaDTO(new Long(1000), "TESTE");
	}

	public static CondicaoMoradia fabricarCondicaoMoradia() {
		return new CondicaoMoradia(new Long(1000), "TESTE");
	}
}
