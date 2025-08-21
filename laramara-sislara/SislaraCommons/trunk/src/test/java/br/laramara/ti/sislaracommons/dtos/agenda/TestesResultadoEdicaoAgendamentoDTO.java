package br.laramara.ti.sislaracommons.dtos.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoAgendamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoagendamentodto_sem_erro_foi_construido() {
		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = new ResultadoEdicaoAgendamentoDTO();
		resultadoEdicaoAgendamentoDto.efetuadoComSucesso(new AgendamentoDTO());

		Assert.assertTrue(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoAgendamentoDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoAgendamentoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoagendamentodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = new ResultadoEdicaoAgendamentoDTO();
		resultadoEdicaoAgendamentoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoAgendamentoDto.obterMensagens());
	}
}
