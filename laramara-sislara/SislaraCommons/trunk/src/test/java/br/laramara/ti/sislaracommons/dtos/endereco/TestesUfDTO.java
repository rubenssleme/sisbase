package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUfDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void ufdto_foi_construido_com_sucesso() {
		String stringUfEsperado = "PA";

		UfDTO ufDto = new UfDTO(stringUfEsperado);

		Assert.assertEquals(stringUfEsperado, ufDto.toString());
	}
}
