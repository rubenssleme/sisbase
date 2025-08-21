package br.laramara.ti.sislaraserver.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEntidadeUtils {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void entidade_utils_incrementa_versao() {
		Long versaoAtual = new Long(2);
		Long versaoEsperada = new Long(3);

		Long versaoObtida = EntidadeUtils.incrementar(versaoAtual);

		Assert.assertEquals(versaoObtida, versaoEsperada);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void entidade_utils_inicializa_versao() {
		Assert.assertNotNull(EntidadeUtils.incrementar(null));
	}
}
