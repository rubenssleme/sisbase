package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoInformacaoEscolarDT {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoinformacaoeducacionaldto_sem_erro_foi_construido() {
		ResultadoValidacaoInformacaoEducacionalDTO resultadoValidacaoInformacaoEducacionalDto = new ResultadoValidacaoInformacaoEducacionalDTO();
		resultadoValidacaoInformacaoEducacionalDto
				.efetuadoComSucesso(new InformacaoEducacionalDTO());

		Assert.assertTrue(resultadoValidacaoInformacaoEducacionalDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoinformacaoeducacionaldto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoInformacaoEducacionalDTO resultadoValidacaoInformacaoEducacionalDto = new ResultadoValidacaoInformacaoEducacionalDTO();
		resultadoValidacaoInformacaoEducacionalDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoInformacaoEducacionalDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoInformacaoEducacionalDto
				.obterMensagens());
	}
}
