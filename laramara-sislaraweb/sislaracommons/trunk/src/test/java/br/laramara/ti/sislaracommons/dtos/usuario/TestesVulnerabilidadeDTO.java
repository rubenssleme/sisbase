package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesVulnerabilidadeDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void vulnerabilidadedto_foi_construida_com_sucesso() {
		Long id = new Long(12);
		String vulnerabilidade = "Vulnerabilidade";

		VulnerabilidadeDTO vulnerabilidadeDto = new VulnerabilidadeDTO(id, vulnerabilidade);

		Assert.assertEquals(vulnerabilidadeDto.getId(), id);
		Assert.assertEquals(vulnerabilidadeDto.toString(), vulnerabilidade);
	}
}
