package br.laramara.ti.sislaracommons.dtos.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoesperadto_foi_construida_com_sucesso() {
		String tipo = "CANCELADO";
		TipoEsperaDTO tipoEsperaDto = new TipoEsperaDTO(tipo);

		Assert.assertEquals(tipoEsperaDto.toString(), tipo);
	}
}
