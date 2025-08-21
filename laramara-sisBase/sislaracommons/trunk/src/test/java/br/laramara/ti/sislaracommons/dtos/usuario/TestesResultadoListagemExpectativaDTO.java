package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemExpectativaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_expectativa_dto_sem_erro_foi_construido() {
		List<ExpectativaDTO> expectativaDto = Arrays.asList(new ExpectativaDTO(new Long(1000), "TESTE"));
		ResultadoListagemExpectativaDTO resultadoListagemExpectativasDto = new ResultadoListagemExpectativaDTO();
		resultadoListagemExpectativasDto.efetuadoComSucesso(expectativaDto);

		Assert.assertTrue(resultadoListagemExpectativasDto.sucesso());
		Assert.assertFalse(resultadoListagemExpectativasDto.getObjetosDto().isEmpty());
	}
}
