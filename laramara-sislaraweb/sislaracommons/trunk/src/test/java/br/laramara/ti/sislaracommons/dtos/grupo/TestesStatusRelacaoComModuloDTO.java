package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusRelacaoComModuloDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusrelacaocommodulodto_foi_construida_com_sucesso() {
		String status = "DESLIGADO";

		StatusRelacaoComModuloDTO statusRelacaoComModuloDto = new StatusRelacaoComModuloDTO(
				status);

		Assert.assertEquals(statusRelacaoComModuloDto.toString(), status);
	}
}
