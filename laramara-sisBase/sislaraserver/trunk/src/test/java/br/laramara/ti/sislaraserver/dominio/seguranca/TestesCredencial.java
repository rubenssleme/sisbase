package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCredencial {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencial_foi_construida_com_sucesso() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";
		String md5 = "698dc19d489c4e4db73e28a713eab07b";

		Credencial credencialEsperada = new Credencial(
				usuarioEsperado, senha, senha);

		Assert.assertEquals(credencialEsperada.getUsuario(), usuarioEsperado);
		Assert.assertEquals(credencialEsperada.getSenha(), md5);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencial_foi_construida_com_nova_senha_correta() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";

		Credencial credencialDtoEsperada = new Credencial(
				usuarioEsperado, senha, senha);

		Assert.assertTrue(credencialDtoEsperada.novaSenhaValida());
	}
}
