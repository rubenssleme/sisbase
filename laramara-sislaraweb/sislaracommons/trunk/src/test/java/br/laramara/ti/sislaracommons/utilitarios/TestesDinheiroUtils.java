package br.laramara.ti.sislaracommons.utilitarios;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDinheiroUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_com_sucesso() {
		String valor = "1.245,99";
		BigDecimal esperado = new BigDecimal("1245.99");

		Assert.assertEquals(esperado,
				DinheiroUtils.obterDinheiroOuInvalido(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_alternativo_com_sucesso() {
		String valor = "1245,99";
		BigDecimal esperado = new BigDecimal("1245.99");

		Assert.assertEquals(esperado,
				DinheiroUtils.obterDinheiroOuInvalido(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_sem_sucesso() {
		String valor = "A1245,99";

		Assert.assertEquals(DinheiroUtils.obterDinheiroInvalido(),
				DinheiroUtils.obterDinheiroOuInvalido(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_alternativo_sem_sucesso() {
		String valor = "1245,9A";

		Assert.assertEquals(DinheiroUtils.obterDinheiroInvalido(),
				DinheiroUtils.obterDinheiroOuInvalido(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_alternativo2_sem_sucesso() {
		String valor = "1245.99";

		Assert.assertEquals(DinheiroUtils.obterDinheiroInvalido(),
				DinheiroUtils.obterDinheiroOuInvalido(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_valida_dinheiro_invalido() {
		BigDecimal dinheiroInvalido = DinheiroUtils.obterDinheiroInvalido();

		Assert.assertTrue(DinheiroUtils.eDinheiroValido(dinheiroInvalido));
	}
}