package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoApoioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoapoiodto_foi_construida_com_sucesso() {
		String tipoApoio = "DV";

		TipoApoioDTO tipoApoioDto = new TipoApoioDTO(tipoApoio);

		Assert.assertEquals(tipoApoioDto.toString(), tipoApoio);
	}
}
