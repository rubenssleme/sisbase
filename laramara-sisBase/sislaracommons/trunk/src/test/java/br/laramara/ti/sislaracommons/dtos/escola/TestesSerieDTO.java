package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSerieDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void situacaodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String serie = "1º SERIE";

		SerieDTO serieDto = new SerieDTO(id, serie);

		Assert.assertEquals(serieDto.getId(), id);
		Assert.assertEquals(serieDto.toString(), serie);
	}
}
