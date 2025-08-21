package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoSincronizacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_sincronizacao_profissionais_dto_sem_erro_foi_construido() {
		ResultadoSincronizacaoDTO resultado = new ResultadoSincronizacaoDTO();
		resultado.efetuadoComSucesso();

		Assert.assertFalse(resultado.obterMensagens().isEmpty());
	}
}
