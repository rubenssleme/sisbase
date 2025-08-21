package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoEdicaoPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoPerfilDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoperfildto_sem_erro_foi_construido() {
		ResultadoEdicaoPerfilDTO resultado = new ResultadoEdicaoPerfilDTO();
		resultado.efetuadoComSucesso(new PerfilDTO());

		Assert.assertNotNull(resultado.obterObjetoDtoEditado());
	}
}
