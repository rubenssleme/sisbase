package br.laramara.ti.sislaraserver.utilitarios;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesExtensoUtils {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void extenso_utils_incrementa_versao() {
		Assert.assertEquals(new ExtensoUtils(new BigDecimal("1234.56")).obterExtenso(), "hum mil e duzentos e trinta e quatro reais e cinquenta e seis centavos");
	}
}
