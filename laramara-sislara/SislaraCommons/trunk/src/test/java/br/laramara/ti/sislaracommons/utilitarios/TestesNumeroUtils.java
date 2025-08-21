package br.laramara.ti.sislaracommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestesNumeroUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_numero_inteiro_valido() {
		String texto = "23";

		Integer esperado = new Integer(texto);

		Assert.assertEquals(esperado,
				NumeroUtils.retornaInteiroOuInvalido(texto));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_numero_inteiro_invalido() {
		String texto = "A23";

		Assert.assertTrue(NumeroUtils.numeroInteiroInvalido(NumeroUtils
				.retornaInteiroOuInvalido(texto)));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_numero_inteiro_nulo() {
		Assert.assertNull(NumeroUtils.retornaInteiroOuInvalido(null));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_numero_longo_valido() {
		String texto = "23";

		Long esperado = new Long(texto);

		Assert.assertEquals(esperado,
				NumeroUtils.retornaLongoOuInvalido(texto));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_numero_longo_invalido() {
		String texto = "A23";

		Assert.assertTrue(NumeroUtils.numeroLongoInvalido(NumeroUtils
				.retornaLongoOuInvalido(texto)));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_renorna_longo_numero_nulo() {
		Assert.assertNull(NumeroUtils.retornaLongoOuInvalido(null));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void numeroutils_obter_texto_do_numero() {
		Assert.assertEquals("12", NumeroUtils.obterTexto(new Integer("12")));
	}

}
