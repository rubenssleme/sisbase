package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.NecessidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesNecessidadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void necessidadedto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String necessidade = "Necessidade";

		NecessidadeDTO necessidadeDto = new NecessidadeDTO(id, necessidade);

		Assert.assertEquals(necessidadeDto.getId(), id);
		Assert.assertEquals(necessidadeDto.toString(), necessidade);
	}
}
