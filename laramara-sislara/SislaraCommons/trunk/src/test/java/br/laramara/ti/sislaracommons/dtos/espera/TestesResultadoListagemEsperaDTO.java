package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemesperadto_sem_erro_foi_construido() {
		List<EsperaDTO> esperaDto = new ArrayList<>();
		esperaDto.add(new EsperaDTO());
		esperaDto.add(new EsperaDTO());
		ResultadoListagemEsperaDTO resultadoDto = new ResultadoListagemEsperaDTO();
		resultadoDto.efetuadoComSucesso(esperaDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
