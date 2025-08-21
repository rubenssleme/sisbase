package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemNecessidadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_necessidade_dto_sem_erro_foi_construido() {
		List<NecessidadeDTO> necessidadeDto = Arrays
				.asList(new NecessidadeDTO(new Long(1000), "TESTE"));
		ResultadoListagemNecessidadeDTO resultadoListagemNecessidadeDto = new ResultadoListagemNecessidadeDTO();
		resultadoListagemNecessidadeDto.efetuadoComSucesso(necessidadeDto);

		Assert.assertTrue(resultadoListagemNecessidadeDto.sucesso());
		Assert.assertFalse(resultadoListagemNecessidadeDto.getObjetosDto().isEmpty());
	}
}
