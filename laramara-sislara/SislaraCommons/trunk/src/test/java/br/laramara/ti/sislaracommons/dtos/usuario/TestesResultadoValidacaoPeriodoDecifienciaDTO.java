package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoPeriodoDecifienciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoperiododeficienciadto_sem_erro_foi_construido() {
		ResultadoValidacaoPeriodoDeficienciaDTO resultadoValidacaoPeriodoDeficienciaDto = new ResultadoValidacaoPeriodoDeficienciaDTO();
		resultadoValidacaoPeriodoDeficienciaDto
				.efetuadoComSucesso(new PeriodoDeficienciaDTO());

		Assert.assertTrue(resultadoValidacaoPeriodoDeficienciaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoperiododeficienciadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoPeriodoDeficienciaDTO resultadoValidacaoPeriodoBeneficioDto = new ResultadoValidacaoPeriodoDeficienciaDTO();
		resultadoValidacaoPeriodoBeneficioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoPeriodoBeneficioDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoPeriodoBeneficioDto
				.obterMensagens());
	}
}
