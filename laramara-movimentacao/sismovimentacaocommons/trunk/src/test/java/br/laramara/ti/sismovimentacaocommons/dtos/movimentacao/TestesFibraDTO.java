package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesFibraDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fibradto_foi_construida_com_sucesso() {

		String fibra = "AAA";
		FibraDTO fibraDto = new FibraDTO(fibra);

		Assert.assertEquals(fibraDto.toString(), fibra);
	}
}
