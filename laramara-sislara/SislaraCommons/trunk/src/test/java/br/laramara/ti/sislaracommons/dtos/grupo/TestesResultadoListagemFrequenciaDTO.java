package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemFrequenciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemfrequenciadto_sem_erro_foi_construido() {
		List<FrequenciaDTO> frequenciasDto = new ArrayList<>();
		frequenciasDto.add(new FrequenciaDTO("AT"));
		frequenciasDto.add(new FrequenciaDTO("FU"));
		ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto = new ResultadoListagemFrequenciaDTO();
		resultadoListagemFrequenciaDto.efetuadoComSucesso(frequenciasDto);

		Assert.assertTrue(resultadoListagemFrequenciaDto.sucesso());
		Assert.assertFalse(resultadoListagemFrequenciaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemfrequenciadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto = new ResultadoListagemFrequenciaDTO();
		resultadoListagemFrequenciaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemFrequenciaDto.sucesso());
		Assert.assertNotNull(resultadoListagemFrequenciaDto.obterMensagens());
	}
}
