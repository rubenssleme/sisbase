package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCondicaoMoradiaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void condicao_moradia_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		CondicaoMoradiaDTO condicaoMoradiaDto = new CondicaoMoradiaDTO(id, texto);

		Assert.assertEquals(condicaoMoradiaDto.getId(), id);
		Assert.assertEquals(condicaoMoradiaDto.toString(), texto);
	}
}
