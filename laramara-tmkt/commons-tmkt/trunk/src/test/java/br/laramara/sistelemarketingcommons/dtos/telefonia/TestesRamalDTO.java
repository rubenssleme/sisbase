package br.laramara.sistelemarketingcommons.dtos.telefonia;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesRamalDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void raaml_dto_foi_construida_com_sucesso() {
		String ramal = "6489";
		RamalDTO ramalDto = new RamalDTO(ramal);

		Assert.assertEquals(ramalDto.getNumero(), ramalDto.getNumero());
	}
}
