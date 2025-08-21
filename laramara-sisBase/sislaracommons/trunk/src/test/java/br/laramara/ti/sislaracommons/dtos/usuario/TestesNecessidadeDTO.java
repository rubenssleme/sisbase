package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesNecessidadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_moradia_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		NecessidadeDTO necessidadeDto = new NecessidadeDTO(id, texto);

		Assert.assertEquals(necessidadeDto.getId(), id);
		Assert.assertEquals(necessidadeDto.toString(), texto);
	}
}
