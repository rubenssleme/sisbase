package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoGeracaoAtendimentosDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaoatendimentodto_sem_erro_foi_construido() {
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDto = new ResultadoGeracaoAtendimentoDTO();
		resultadoGeracaoAtendimentoDto.efetuadoComSucesso();

		Assert.assertTrue(resultadoGeracaoAtendimentoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaoatendimentodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDto = new ResultadoGeracaoAtendimentoDTO();
		resultadoGeracaoAtendimentoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoGeracaoAtendimentoDto.sucesso());
	}
}
