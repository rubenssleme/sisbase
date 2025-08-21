package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoPeriodoBeneficioDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoperiodobeneficiodto_sem_erro_foi_construido() {
		ResultadoValidacaoPeriodoBeneficioDTO resultadoValidacaoPeriodoBeneficioDto = new ResultadoValidacaoPeriodoBeneficioDTO();
		resultadoValidacaoPeriodoBeneficioDto.efetuadoComSucesso(new PeriodoBeneficioDTO());
				

		Assert.assertTrue(resultadoValidacaoPeriodoBeneficioDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoperiodobeneficiodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoPeriodoBeneficioDTO resultadoValidacaoPeriodoBeneficioDto = new ResultadoValidacaoPeriodoBeneficioDTO();
		resultadoValidacaoPeriodoBeneficioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoPeriodoBeneficioDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoPeriodoBeneficioDto.obterMensagens());
	}
}
