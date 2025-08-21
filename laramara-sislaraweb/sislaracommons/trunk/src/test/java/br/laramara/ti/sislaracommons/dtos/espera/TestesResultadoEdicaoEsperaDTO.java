package br.laramara.ti.sislaracommons.dtos.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoEsperaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoesperadto_sem_erro_foi_construido() {
		ResultadoEdicaoEsperaDTO resultadoEdicaoEsperaDto = new ResultadoEdicaoEsperaDTO();
		resultadoEdicaoEsperaDto.efetuadoComSucesso(new EsperaDTO());

		Assert.assertTrue(resultadoEdicaoEsperaDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoEsperaDto.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoEsperaDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoesperadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoEsperaDTO resultadoEdicaoEsperaDto = new ResultadoEdicaoEsperaDTO();
		resultadoEdicaoEsperaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoEsperaDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoEsperaDto.obterMensagens());
	}
}
