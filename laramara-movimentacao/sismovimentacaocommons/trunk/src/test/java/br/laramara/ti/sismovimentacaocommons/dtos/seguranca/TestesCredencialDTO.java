package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesCredencialDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencialdto_foi_construida_com_sucesso() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";
		String md5 = "698dc19d489c4e4db73e28a713eab07b";

		CredencialDTO credencialDtoEsperada = new CredencialDTO(
				usuarioEsperado, senha);

		Assert.assertEquals(credencialDtoEsperada.getUsuario(), usuarioEsperado);
		Assert.assertEquals(credencialDtoEsperada.getSenha(), md5);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencialdto_foi_construida_com_nova_senha_correta() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";
		String confirmacaoSenha = "teste";

		CredencialDTO credencialDtoEsperada = new CredencialDTO(
				usuarioEsperado, senha, confirmacaoSenha);

		Assert.assertTrue(credencialDtoEsperada.novaSenhaValida());
	}
}
