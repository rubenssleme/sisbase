package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemSerieDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemseriedto_sem_erro_foi_construido() {
		List<SerieDTO> serieDto = new ArrayList<>();
		serieDto.add(new SerieDTO(new Long(1), "1º Serie"));
		ResultadoListagemSerieDTO resultadoListagemSerieDto = new ResultadoListagemSerieDTO();
		resultadoListagemSerieDto.efetuadoComSucesso(serieDto);

		Assert.assertTrue(resultadoListagemSerieDto.sucesso());
		Assert.assertFalse(resultadoListagemSerieDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemseriedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemSerieDTO resultadoListagemSerieDto = new ResultadoListagemSerieDTO();
		resultadoListagemSerieDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemSerieDto.sucesso());
		Assert.assertNotNull(resultadoListagemSerieDto.obterMensagens());
	}
}
