package br.laramara.ti.sislaracommons.dtos.familiar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesParentescoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void parentescodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String parentesco = "PAI";

		ParentescoDTO parentescoDto = new ParentescoDTO(id, "PAI");

		Assert.assertEquals(parentescoDto.getId(), id);
		Assert.assertEquals(parentescoDto.toString(), parentesco);
	}
}
