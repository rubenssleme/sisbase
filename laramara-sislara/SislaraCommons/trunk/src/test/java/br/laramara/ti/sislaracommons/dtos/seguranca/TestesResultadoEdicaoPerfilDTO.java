package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoPerfilDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoperfildto_sem_erro_foi_construido() {
		ResultadoEdicaoPerfilDTO resultado = new ResultadoEdicaoPerfilDTO();
		resultado.efetuadoComSucesso(new PerfilDTO());

		Assert.assertNotNull(resultado.obterObjetoDtoEditado());
	}
}
