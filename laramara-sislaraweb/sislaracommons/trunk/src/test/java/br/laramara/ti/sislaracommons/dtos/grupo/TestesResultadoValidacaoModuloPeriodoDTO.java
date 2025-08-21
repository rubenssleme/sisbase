package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoModuloPeriodoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaomoduloperiododto_sem_erro_foi_construido() {
		ResultadoValidacaoModuloPeriodoDTO resultadoValidacaoModuloPeriodoDto = new ResultadoValidacaoModuloPeriodoDTO();
		resultadoValidacaoModuloPeriodoDto
				.efetuadoComSucesso(new ModuloPeriodoDTO());

		Assert.assertTrue(resultadoValidacaoModuloPeriodoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaomoduloperiododto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoModuloPeriodoDTO resultadoValidacaoModuloPeriodoDto = new ResultadoValidacaoModuloPeriodoDTO();
		resultadoValidacaoModuloPeriodoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoModuloPeriodoDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoModuloPeriodoDto
				.obterMensagens());
	}
}
