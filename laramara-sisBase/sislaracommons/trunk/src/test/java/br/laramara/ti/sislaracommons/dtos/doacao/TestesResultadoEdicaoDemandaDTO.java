package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaodemandadto_sem_erro_foi_construido() {
		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDto = new ResultadoEdicaoDemandaDTO();
		resultadoEdicaoDemandaDto.efetuadoComSucesso(new DemandaDTO());

		Assert.assertTrue(resultadoEdicaoDemandaDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoDemandaDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoDemandaDto.obterMensagens());
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
