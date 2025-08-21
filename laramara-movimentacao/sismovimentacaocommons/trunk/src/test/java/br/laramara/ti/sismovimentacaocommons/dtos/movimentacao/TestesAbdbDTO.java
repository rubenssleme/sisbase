package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesAbdbDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void abdbdto_foi_construida_com_sucesso() {

		String abdb = "AAA";
		AbdbDTO abdbDto = new AbdbDTO(abdb);

		Assert.assertEquals(abdbDto.toString(), abdb);
	}
}
