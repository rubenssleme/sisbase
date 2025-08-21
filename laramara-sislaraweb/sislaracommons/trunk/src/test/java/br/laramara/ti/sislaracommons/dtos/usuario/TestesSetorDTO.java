package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSetorDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void setordto_foi_construido_com_sucesso() {
		String stringSetorEsperado = "CTO";

		SetorDTO setorDto = new SetorDTO(stringSetorEsperado);

		Assert.assertEquals(stringSetorEsperado, setorDto.toString());
	}
}
