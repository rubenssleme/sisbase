package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesSimNaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void simnaodto_foi_construida_com_sucesso() {

		String simnao = "SIM";
		SimNaoDTO simnaoDto = new SimNaoDTO(simnao);

		Assert.assertEquals(simnaoDto.toString(), simnao);
	}
}
