package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemStatusEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemstatusesperadto_sem_erro_foi_construido() {
		List<StatusEsperaDTO> statusEsperaDto = new ArrayList<>();
		statusEsperaDto.add(new StatusEsperaDTO("AGENDADO"));
		statusEsperaDto.add(new StatusEsperaDTO("CANCELADO"));
		ResultadoListagemStatusEsperaDTO resultadoDto = new ResultadoListagemStatusEsperaDTO();
		resultadoDto.efetuadoComSucesso(statusEsperaDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
