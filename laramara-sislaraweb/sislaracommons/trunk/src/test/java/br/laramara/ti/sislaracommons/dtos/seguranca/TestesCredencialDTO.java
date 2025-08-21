package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesCredencialDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void credencialdto_foi_construida_com_sucesso() {
		String usuarioEsperado = "pabsantos";
		String senha = "teste";
		String confirmacaoSenha = "teste";

		CredencialDTO credencialDtoEsperada = new CredencialDTO(usuarioEsperado, senha, confirmacaoSenha);

		Assert.assertEquals(credencialDtoEsperada.getUsuario(), usuarioEsperado);
		Assert.assertEquals(credencialDtoEsperada.getSenha(), senha);
		Assert.assertEquals(credencialDtoEsperada.getConfirmacaoSenha(), confirmacaoSenha);
	}
}
