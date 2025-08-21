package br.laramara.ti.sislaracommons.dtos.contribuicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusContribuinteDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statuscontribuintedto_foi_construido_com_sucesso() {
		String string = "ATIVADO";

		StatusContribuinteDTO statusContribuinteDto = new StatusContribuinteDTO(string);

		Assert.assertEquals(string, statusContribuinteDto.toString());
	}
}
