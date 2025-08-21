package br.laramara.ti.sislaracommons.dtos.trabalho;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesLocalTrabalhoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void localtrabalhodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String localTrabalho = "ctis";

		LocalTrabalhoDTO cargoDto = new LocalTrabalhoDTO(id, localTrabalho);

		Assert.assertEquals(cargoDto.getId(), id);
		Assert.assertEquals(cargoDto.toString(), localTrabalho);
	}
}
