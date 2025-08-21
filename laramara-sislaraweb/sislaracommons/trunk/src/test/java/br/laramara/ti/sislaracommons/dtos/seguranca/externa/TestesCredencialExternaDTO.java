package br.laramara.ti.sislaracommons.dtos.seguranca.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCredencialExternaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencialexternadto_foi_construida_com_sucesso() {
		String emailEsperado = "usuario.externo@gmail.com";
		String senha = "1234";

		CredencialExternaDTO credencialExternaDtoEsperada = new CredencialExternaDTO(emailEsperado, senha);

		Assert.assertFalse(credencialExternaDtoEsperada.toString().isEmpty());
		Assert.assertEquals(credencialExternaDtoEsperada.getEmail(), emailEsperado);
		Assert.assertEquals(credencialExternaDtoEsperada.getSenha(), senha);
	}
}
