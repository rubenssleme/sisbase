package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSimNaoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void simnaodto_foi_construido_com_sucesso() {
		String simNao = "SIM";

		SimNaoDTO simNaoDto = new SimNaoDTO(simNao);

		Assert.assertEquals(simNao, simNaoDto.toString());
	}
}
