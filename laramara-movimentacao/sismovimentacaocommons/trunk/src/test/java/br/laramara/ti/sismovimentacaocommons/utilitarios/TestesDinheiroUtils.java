package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesDinheiroUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_para_dinheiro_com_sucesso() {
		String valor = "1.245,99";
		String esperado = "1245.99";

		Assert.assertEquals(esperado,
				DinheiroUtils.converterParaDinheiro(valor).toString());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_invalida_valor_sem_virgula() {
		String valor = "1.245.99";

		Assert.assertFalse(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_invalida_valor_vazio() {
		String valor = "";

		Assert.assertFalse(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_invalida_valor_com_caracteres_nao_numericos() {
		String valor = "1.2s45.99";

		Assert.assertFalse(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_invalida_valor_sem_decimal_de_centavos() {
		String valor = "1245";

		Assert.assertFalse(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_invalida_valor_com_virgula_a_mais() {
		String valor = "1,234567,89";

		Assert.assertFalse(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_valida_valor_com_sucesso() {
		String valor = "1234567,89";

		Assert.assertTrue(DinheiroUtils.validaParaDinheiro(valor));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dinheiroutils_converte_de_valor_para_string() {
		BigDecimal valor = new BigDecimal("1245.99");

		Assert.assertEquals("1245,99", DinheiroUtils.converterDinheiroParaString(valor));
	}
	
	public void dinheiroutils_data_preenchida() {
		Assert.assertTrue(DinheiroUtils.dinheiroPreenchida("122.11"));
	}
}