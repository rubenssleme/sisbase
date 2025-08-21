package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemHabitacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_habitacao_dto_sem_erro_foi_construido() {
		List<HabitacaoDTO> habitacaoDto = Arrays
				.asList(new HabitacaoDTO(new Long(1000), "TESTE"));
		ResultadoListagemHabitacaoDTO resultadoListagemHabitacaoDto = new ResultadoListagemHabitacaoDTO();
		resultadoListagemHabitacaoDto.efetuadoComSucesso(habitacaoDto);

		Assert.assertTrue(resultadoListagemHabitacaoDto.sucesso());
		Assert.assertFalse(resultadoListagemHabitacaoDto.getObjetosDto().isEmpty());
	}
}
