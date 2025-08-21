package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesModuloDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		String nome = "Informática";

		ModuloDTO moduloDto = new ModuloDTO(id, nome);

		Assert.assertEquals(moduloDto.getId(), id);
		Assert.assertEquals(moduloDto.toString(), nome);
	}
}
