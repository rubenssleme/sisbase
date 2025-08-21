package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesPapelDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void papeldto_foi_construida_com_sucesso() {

		String papel = "AAA";
		PapelDTO papelDto = new PapelDTO(papel);

		Assert.assertEquals(papelDto.toString(), papel);
	}
}
