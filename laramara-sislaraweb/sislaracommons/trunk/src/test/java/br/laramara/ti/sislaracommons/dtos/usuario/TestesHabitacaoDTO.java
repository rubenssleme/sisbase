package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesHabitacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void habitacao_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		HabitacaoDTO habitacaoDto = new HabitacaoDTO(id, texto);

		Assert.assertEquals(habitacaoDto.getId(), id);
		Assert.assertEquals(habitacaoDto.toString(), texto);
	}
}
