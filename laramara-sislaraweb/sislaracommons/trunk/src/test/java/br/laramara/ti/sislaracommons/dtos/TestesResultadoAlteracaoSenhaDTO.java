package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoAlteracaoSenhaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoalteracaosenhadto_sem_erro_foi_construido() {
		ResultadoAlteracaoSenhaDTO resultadoResultadoAlteracaoSenhaDto = new ResultadoAlteracaoSenhaDTO();
		resultadoResultadoAlteracaoSenhaDto.efetuadoComSucesso();

		Assert.assertTrue(resultadoResultadoAlteracaoSenhaDto.sucesso());
		Assert.assertFalse(resultadoResultadoAlteracaoSenhaDto.obterMensagens()
				.isEmpty());
	}
}
