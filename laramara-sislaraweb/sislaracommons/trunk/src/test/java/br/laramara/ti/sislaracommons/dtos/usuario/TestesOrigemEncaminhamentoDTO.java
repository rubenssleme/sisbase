package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesOrigemEncaminhamentoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void origemencaminhamentodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String descricao = "Hospital";

		OrigemEncaminhamentoDTO origemEncaminhamentoDto = new OrigemEncaminhamentoDTO(id, descricao);

		Assert.assertEquals(origemEncaminhamentoDto.getId(), id);
		Assert.assertEquals(origemEncaminhamentoDto.toString(), descricao);
	}
}
