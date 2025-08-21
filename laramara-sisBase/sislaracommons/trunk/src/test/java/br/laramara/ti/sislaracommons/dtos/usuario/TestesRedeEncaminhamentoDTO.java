package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRedeEncaminhamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void redeencaminhamentodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String descricao = "SUAS";

		RedeEncaminhamentoDTO redeEncaminhamentoDto = new RedeEncaminhamentoDTO(id, descricao);

		Assert.assertEquals(redeEncaminhamentoDto.getId(), id);
		Assert.assertEquals(redeEncaminhamentoDto.toString(), descricao);
	}
}
