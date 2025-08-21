package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEncaminhamentoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void encaminhamentodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String descricao = "Hospital";

		EncaminhamentoDTO encaminhamentoDto = new EncaminhamentoDTO(id, descricao);

		Assert.assertEquals(encaminhamentoDto.getId(), id);
		Assert.assertEquals(encaminhamentoDto.toString(), descricao);
	}
}
