package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemSituacaoHabitacionalDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_situacao_habitacional_dto_sem_erro_foi_construido() {
		List<SituacaoHabitacionalDTO> situacaoHabitacionalDto = Arrays
				.asList(new SituacaoHabitacionalDTO(new Long(1000), "TESTE"));
		ResultadoListagemSituacaoHabitacionalDTO resultadoListagemSituacaoHabitacionalDto = new ResultadoListagemSituacaoHabitacionalDTO();
		resultadoListagemSituacaoHabitacionalDto.efetuadoComSucesso(situacaoHabitacionalDto);

		Assert.assertTrue(resultadoListagemSituacaoHabitacionalDto.sucesso());
		Assert.assertFalse(resultadoListagemSituacaoHabitacionalDto.getObjetosDto().isEmpty());
	}
}
