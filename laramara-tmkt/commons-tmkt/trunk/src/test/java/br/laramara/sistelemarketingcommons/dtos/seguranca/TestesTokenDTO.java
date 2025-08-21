package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesTokenDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void token_dto_foi_construida_com_sucesso() {
		String token = "434042309234987sdkjahsad";

		TokenDTO tokenDto = new TokenDTO(token);

		Assert.assertEquals(tokenDto.getToken(), token);
	}
}
