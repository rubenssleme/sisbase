package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTokenDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tokendto_foi_construido_com_sucesso() {
		String tokenEsperado = "7dc53df5-703e-49b3-8670-b1c468f47f1f";

		TokenDTO tokenDto = new TokenDTO(tokenEsperado);

		Assert.assertEquals(tokenDto.getToken(), tokenEsperado);
	}
}
