package br.laramara.ti.sislaracommons.dtos.trabalho;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCargoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cargodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String cargo = "Administrador";

		CargoDTO cargoDto = new CargoDTO(id, cargo);

		Assert.assertEquals(cargoDto.getId(), id);
		Assert.assertEquals(cargoDto.toString(), cargo);
	}
}
