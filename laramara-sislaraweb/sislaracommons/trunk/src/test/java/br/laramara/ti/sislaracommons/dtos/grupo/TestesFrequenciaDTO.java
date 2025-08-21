package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesFrequenciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void frequenciadto_foi_construida_com_sucesso() {
		String frequencia = "AT";

		FrequenciaDTO frequenciaDto = new FrequenciaDTO(frequencia);

		Assert.assertEquals(frequenciaDto.toString(), frequencia);
	}
}
