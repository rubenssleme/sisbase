package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesExpectativaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void expectativa_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		ExpectativaDTO expectativaDto = new ExpectativaDTO(id, texto);

		Assert.assertEquals(expectativaDto.getId(), id);
		Assert.assertEquals(expectativaDto.toString(), texto);
	}
}
