package br.laramara.ti.sislaracommons.dtos.familiar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoFamiliarDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaofamiliardto_sem_erro_foi_construido() {
		ResultadoValidacaoFamiliarDTO resultadoValidacaoFamiliarDto = new ResultadoValidacaoFamiliarDTO();
		resultadoValidacaoFamiliarDto.efetuadoComSucesso(new FamiliarDTO());

		Assert.assertTrue(resultadoValidacaoFamiliarDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaofamiliardto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoFamiliarDTO resultadoValidacaoFamiliarDto = new ResultadoValidacaoFamiliarDTO();
		resultadoValidacaoFamiliarDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoFamiliarDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoFamiliarDto.obterMensagens());
	}
}
