package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoCoordenacaoEdicaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadocoordenacaoedicaodto_sem_erro_foi_construido() {
		ResultadoCoordenacaoEdicaoDTO resultadoCoordenacaoEdicaoDto = new ResultadoCoordenacaoEdicaoDTO();

		resultadoCoordenacaoEdicaoDto.efetuadoComSucesso(new ContaAcessoDTO());

		Assert.assertTrue(resultadoCoordenacaoEdicaoDto.sucesso());
		Assert.assertNotNull(resultadoCoordenacaoEdicaoDto.obterMensagens());
	}
}
