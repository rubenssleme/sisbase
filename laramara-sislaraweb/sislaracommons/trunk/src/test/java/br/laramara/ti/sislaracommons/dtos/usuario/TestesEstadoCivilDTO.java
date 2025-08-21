package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEstadoCivilDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void estadocivildto_foi_construido_com_sucesso() {
		Long id = new Long(1);
		String stringEstadoCivilEsperado = "SOLTEIRO";

		EstadoCivilDTO estadoCivilDto = new EstadoCivilDTO(id, stringEstadoCivilEsperado);

		Assert.assertEquals(id, estadoCivilDto.getId());
		Assert.assertEquals(stringEstadoCivilEsperado, estadoCivilDto.toString());
	}
}
