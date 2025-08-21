package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesClassificacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void classificacaodto_foi_construida_com_sucesso() {

		String classificacao = "BRANCO";
		ClassificacaoDTO classificacaoDto = new ClassificacaoDTO(classificacao);

		Assert.assertEquals(classificacaoDto.toString(), classificacao);
	}
}
