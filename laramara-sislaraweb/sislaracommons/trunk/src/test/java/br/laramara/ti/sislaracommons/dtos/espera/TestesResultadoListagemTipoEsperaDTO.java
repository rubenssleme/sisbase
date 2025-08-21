package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoesperadto_sem_erro_foi_construido() {
		List<TipoEsperaDTO> tipoEsperaDto = new ArrayList<>();
		tipoEsperaDto.add(new TipoEsperaDTO("LU"));
		tipoEsperaDto.add(new TipoEsperaDTO("LC"));
		ResultadoListagemTipoEsperaDTO resultadoDto = new ResultadoListagemTipoEsperaDTO();
		resultadoDto.efetuadoComSucesso(tipoEsperaDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
