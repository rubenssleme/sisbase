package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesGeneroDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void generodto_foi_construido_com_sucesso() {
		String stringGeneroEsperado = "MASCULINO";

		GeneroDTO generoDto = new GeneroDTO(stringGeneroEsperado);

		Assert.assertEquals(stringGeneroEsperado, generoDto.toString());
	}
}
