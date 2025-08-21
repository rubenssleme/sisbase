package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEpocaIncidenciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void epocaincidenciadto_foi_construido_com_sucesso() {
		String stringEpocaIncidencia = "ADQUIRIDA";

		EpocaIncidenciaDTO generoDto = new EpocaIncidenciaDTO(
				stringEpocaIncidencia);

		Assert.assertEquals(stringEpocaIncidencia, generoDto.toString());
	}
}
