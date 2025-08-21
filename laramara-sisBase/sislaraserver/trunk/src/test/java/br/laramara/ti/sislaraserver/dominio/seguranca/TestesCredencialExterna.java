package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCredencialExterna {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencial_externa_foi_construida_com_sucesso() {
		String emailEsperado = "usuario.externo@gmail.com";
		String senha = "1234";
		String md5 = "81dc9bdb52d04dc20036dbd8313ed055";

		CredencialExterna credencialEsperada = new CredencialExterna(
				emailEsperado, senha);

		Assert.assertEquals(credencialEsperada.getEmail(), emailEsperado);
		Assert.assertEquals(credencialEsperada.getSenha(), md5);
	}

}
