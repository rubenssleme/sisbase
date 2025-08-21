package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCredencialDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencial_dto_foi_construida_com_sucesso() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";
		RamalDTO ramal = new RamalDTO("6489");

		CredencialDTO credencialDtoEsperada = new CredencialDTO(usuarioEsperado, senha, ramal);

		Assert.assertEquals(credencialDtoEsperada.getUsuario(), usuarioEsperado);
		Assert.assertEquals(credencialDtoEsperada.getSenha(), senha);
		Assert.assertEquals(credencialDtoEsperada.getRamalDto(), ramal);
	}
}
