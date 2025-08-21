package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAreaFormacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void areaformacaodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String areaFormacao = "Direito";

		AreaFormacaoDTO areaFormacaoDto = new AreaFormacaoDTO(id, areaFormacao);

		Assert.assertEquals(areaFormacaoDto.getId(), id);
		Assert.assertEquals(areaFormacaoDto.toString(), areaFormacao);
	}
}
