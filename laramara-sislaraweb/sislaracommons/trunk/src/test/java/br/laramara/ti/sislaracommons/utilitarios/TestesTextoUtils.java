package br.laramara.ti.sislaracommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTextoUtils {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_anula_string_inexistente() {
		String texto = null;

		Assert.assertNull(TextoUtils.removerCaracteresInvalidosEAnularVazio(texto));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_remove_espaco_texto() {
		String texto = "AA AA";

		Assert.assertEquals(TextoUtils.removerCaracteresInvalidosEAnularVazio(texto),
				"AAAA");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_remove_ponto_traco_texto_rg() {
		String texto = "123.123-123";

		Assert.assertEquals(TextoUtils.removerCaracteresInvalidosRG(texto),
				"123123123");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_retorna_texto_vazio_a_partir_de_valor_nulo() {
		String texto = null;

		Assert.assertEquals(TextoUtils.removerCaracteresInvalidosRG(texto), "");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_anula_vazio() {
		String texto = "";

		Assert.assertNull(TextoUtils.anularVazio(texto));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_texto_vazio() {
		String texto = "";

		Assert.assertTrue(TextoUtils.estaVazio(texto));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_remover_chave() {
		String texto = "123[4]5";

		Assert.assertEquals("12345", TextoUtils.removerChaves(texto));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_remover_virgula_final() {
		String texto = "Informática, Inglês, ";

		Assert.assertEquals("Informática, Inglês",
				TextoUtils.removerVirgulaFinal(texto));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void textoutils_substituir_conchetes() {
		String texto = "[1, 2, 3]";

		Assert.assertEquals("(1, 2, 3)", TextoUtils.substituirCaracteres(texto));
	}

}
