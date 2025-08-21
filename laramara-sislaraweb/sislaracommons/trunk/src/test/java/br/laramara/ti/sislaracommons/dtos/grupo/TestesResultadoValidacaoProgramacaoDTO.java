package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoProgramacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoprogramacaodto_sem_erro_foi_construido() {
		ResultadoValidacaoProgramacaoDTO resultadoValidacaoProgramacaoDto = new ResultadoValidacaoProgramacaoDTO();
		resultadoValidacaoProgramacaoDto
				.efetuadoComSucesso(new ProgramacaoDTO());

		Assert.assertTrue(resultadoValidacaoProgramacaoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoprogramacaodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoProgramacaoDTO resultadoValidacaoProgramacaoDto = new ResultadoValidacaoProgramacaoDTO();
		resultadoValidacaoProgramacaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoProgramacaoDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoProgramacaoDto.obterMensagens());
	}
}
