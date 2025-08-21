package br.laramara.ti.sislaracommons.dtos.contribuicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoContribuinteDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaocontribuintedto_sem_erro_foi_construido() {
		ResultadoEdicaoContribuinteDTO resultadoEdicaoContribuinteDto = new ResultadoEdicaoContribuinteDTO();
		resultadoEdicaoContribuinteDto
				.efetuadoComSucesso(new ContribuinteDTO());

		Assert.assertTrue(resultadoEdicaoContribuinteDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoContribuinteDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoContribuinteDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaocontribuintedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoContribuinteDTO resultadoEdicaoContribuinteDto = new ResultadoEdicaoContribuinteDTO();
		resultadoEdicaoContribuinteDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoContribuinteDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoContribuinteDto.obterMensagens());
	}
}
