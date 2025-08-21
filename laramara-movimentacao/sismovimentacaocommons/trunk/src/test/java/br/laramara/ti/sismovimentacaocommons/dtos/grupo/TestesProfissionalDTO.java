package br.laramara.ti.sismovimentacaocommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesProfissionalDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void profissionaldto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		String nome = "Paulo Augusto";
		String chapa = "12222";

		ProfissionalDTO profissionalDto = new ProfissionalDTO(id, nome, chapa);

		Assert.assertEquals(profissionalDto.getId(), id);
		Assert.assertEquals(profissionalDto.toString(), nome);
		Assert.assertEquals(profissionalDto.getChapa(), chapa);
	}
}
