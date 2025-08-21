package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemPeriodoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemperiododto_sem_erro_foi_construido() {
		List<PeriodoDTO> periodoDto = new ArrayList<>();
		periodoDto.add(new PeriodoDTO("MANHA"));
		ResultadoListagemPeriodoDTO resultadoListagemPeriodoDto = new ResultadoListagemPeriodoDTO();
		resultadoListagemPeriodoDto.efetuadoComSucesso(periodoDto);

		Assert.assertTrue(resultadoListagemPeriodoDto.sucesso());
		Assert.assertFalse(resultadoListagemPeriodoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemperiododto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemPeriodoDTO resultadoListagemPeriodoDto = new ResultadoListagemPeriodoDTO();
		resultadoListagemPeriodoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemPeriodoDto.sucesso());
		Assert.assertNotNull(resultadoListagemPeriodoDto.obterMensagens());
	}
}
