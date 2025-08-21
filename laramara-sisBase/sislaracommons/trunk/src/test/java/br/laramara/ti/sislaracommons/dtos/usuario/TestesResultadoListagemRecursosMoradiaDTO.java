package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemRecursosMoradiaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_recursos_moradia_dto_sem_erro_foi_construido() {
		List<RecursoMoradiaDTO> recursoMoradiaDto = Arrays
				.asList(new RecursoMoradiaDTO(new Long(1000), "TESTE"));
		ResultadoListagemRecursoMoradiaDTO resultadoListagemRecursoMoradiaDto = new ResultadoListagemRecursoMoradiaDTO();
		resultadoListagemRecursoMoradiaDto.efetuadoComSucesso(recursoMoradiaDto);

		Assert.assertTrue(resultadoListagemRecursoMoradiaDto.sucesso());
		Assert.assertFalse(resultadoListagemRecursoMoradiaDto.getObjetosDto().isEmpty());
	}
}
