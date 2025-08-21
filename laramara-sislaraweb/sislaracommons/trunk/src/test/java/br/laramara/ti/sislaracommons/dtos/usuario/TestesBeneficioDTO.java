package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesBeneficioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void beneficiosdto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "Aposentadoria Por Invalidez";

		BeneficioDTO beneficioDto = new BeneficioDTO(id, descricao);

		Assert.assertEquals(beneficioDto.getId(), id);
		Assert.assertEquals(beneficioDto.toString(), descricao);
	}
}
