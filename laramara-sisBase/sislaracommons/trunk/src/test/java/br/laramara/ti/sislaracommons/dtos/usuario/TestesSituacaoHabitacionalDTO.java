package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSituacaoHabitacionalDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void situacao_habitacional_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		SituacaoHabitacionalDTO situacaoHabitacionalDto = new SituacaoHabitacionalDTO(id, texto);

		Assert.assertEquals(situacaoHabitacionalDto.getId(), id);
		Assert.assertEquals(situacaoHabitacionalDto.toString(), texto);
	}
}
