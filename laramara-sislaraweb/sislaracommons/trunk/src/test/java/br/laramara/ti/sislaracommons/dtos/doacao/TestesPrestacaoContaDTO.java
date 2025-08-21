package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPrestacaoContaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void prestacao_conta_dto_foi_construida_com_sucesso() {
		String mensal = "MENSAL";
		
		PrestacaoContaDTO prestacaoContaDto = new PrestacaoContaDTO(mensal);

		Assert.assertEquals(prestacaoContaDto.toString(), mensal);
	}
}
