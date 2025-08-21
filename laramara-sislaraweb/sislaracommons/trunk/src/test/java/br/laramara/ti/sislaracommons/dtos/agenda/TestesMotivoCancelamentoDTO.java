package br.laramara.ti.sislaracommons.dtos.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesMotivoCancelamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void motivocancelamentodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		String descricao = "Cancelou sem explicação.";

		MotivoCancelamentoDTO profissionalDto = new MotivoCancelamentoDTO(id,
				descricao);

		Assert.assertEquals(profissionalDto.getId(), id);
		Assert.assertEquals(profissionalDto.toString(), descricao);
	}
}
