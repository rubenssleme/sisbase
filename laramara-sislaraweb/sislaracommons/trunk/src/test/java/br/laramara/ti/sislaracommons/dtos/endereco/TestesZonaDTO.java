package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesZonaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void zonasdto_foi_construido_com_sucesso() {
		String stringZonaEsperado = "NORTE";

		ZonaDTO zonaDto = new ZonaDTO(stringZonaEsperado);

		Assert.assertEquals(stringZonaEsperado, zonaDto.toString());
	}
}
