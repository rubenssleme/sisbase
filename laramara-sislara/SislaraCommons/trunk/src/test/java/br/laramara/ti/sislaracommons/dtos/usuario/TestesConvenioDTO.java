package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesConvenioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conveniodto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "SEFA";

		ConvenioDTO convenioDto = new ConvenioDTO(id, descricao);

		Assert.assertEquals(convenioDto.getId(), id);
		Assert.assertEquals(convenioDto.toString(), descricao);
	}
}
