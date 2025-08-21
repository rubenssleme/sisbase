package br.laramara.ti.sislaracommons.dtos.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoEdicaoAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoAtendimentoIndividualDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoatendimentoindividualdto_sem_erro_foi_construido() {
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoDto = new ResultadoEdicaoAtendimentoIndividualDTO();
		resultadoEdicaoAtendimentoDto
				.efetuadoComSucesso(new AtendimentoIndividualDTO());

		Assert.assertTrue(resultadoEdicaoAtendimentoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoAtendimentoDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoAtendimentoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoatendimentoindividualdto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoDto = new ResultadoEdicaoAtendimentoIndividualDTO();
		resultadoEdicaoAtendimentoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoAtendimentoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoAtendimentoDto.obterMensagens());
	}
}
