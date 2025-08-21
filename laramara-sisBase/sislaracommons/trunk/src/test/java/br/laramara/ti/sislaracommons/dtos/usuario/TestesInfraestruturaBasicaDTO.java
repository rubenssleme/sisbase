package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInfraestruturaBasicaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void infraestrutura_basico_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		InfraestruturaBasicaDTO infraestruturaBasicoDto = new InfraestruturaBasicaDTO(id, texto);

		Assert.assertEquals(infraestruturaBasicoDto.getId(), id);
		Assert.assertEquals(infraestruturaBasicoDto.toString(), texto);
	}
}
