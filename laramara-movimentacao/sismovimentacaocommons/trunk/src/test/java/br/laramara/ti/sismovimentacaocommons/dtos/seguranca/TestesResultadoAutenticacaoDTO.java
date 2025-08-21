package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesResultadoAutenticacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoautenticacaodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = new ResultadoAutenticacaoDTO();
		resultadoAutenticacaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoAutenticacaoDto.sucesso());
		Assert.assertNotNull(resultadoAutenticacaoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoautenticacaodto_sem_erro_foi_construido() {
		TokenDTO tokenEsperado = new TokenDTO(
				"7dc53df5-703e-49b3-8670-b1c468f47f1f");

		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = new ResultadoAutenticacaoDTO();
		resultadoAutenticacaoDTO.efetuadoComSucesso(tokenEsperado);

		Assert.assertTrue(resultadoAutenticacaoDTO.sucesso());
		Assert.assertEquals(resultadoAutenticacaoDTO.getToken(), tokenEsperado);
	}
}
