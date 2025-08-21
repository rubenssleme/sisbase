package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ExpectativaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesExpectativaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void expectativadto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String expectativa = "Expectativa";

		ExpectativaDTO expectativaDto = new ExpectativaDTO(id, expectativa);

		Assert.assertEquals(expectativaDto.getId(), id);
		Assert.assertEquals(expectativaDto.toString(), expectativa);
	}
}
