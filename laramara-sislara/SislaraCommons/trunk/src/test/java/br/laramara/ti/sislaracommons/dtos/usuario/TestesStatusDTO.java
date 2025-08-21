package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusdto_foi_construido_com_sucesso() {
		String stringStatusEsperado = "NOVO";

		StatusDTO statusDto = new StatusDTO(stringStatusEsperado);

		Assert.assertEquals(stringStatusEsperado, statusDto.toString());
	}
}
