package br.laramara.ti.sislaracommons.dtos.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusesperadto_foi_construida_com_sucesso() {
		String status = "CANCELADO";
		StatusEsperaDTO statusEsperaDto = new StatusEsperaDTO(status);

		Assert.assertEquals(statusEsperaDto.toString(), status);
	}
}
