package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusDemandaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusdemandadto_foi_construido_com_sucesso() {
		String string = "DEMANDA";

		StatusDemandaDTO demandaDto = new StatusDemandaDTO(string);

		Assert.assertEquals(string, demandaDto.toString());
	}
}
