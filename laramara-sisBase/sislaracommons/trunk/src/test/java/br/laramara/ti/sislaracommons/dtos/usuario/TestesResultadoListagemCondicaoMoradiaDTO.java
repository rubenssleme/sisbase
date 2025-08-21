package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemCondicaoMoradiaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_condicao_moradia_dto_sem_erro_foi_construido() {
		List<CondicaoMoradiaDTO> condicaoMoradiaDto = Arrays.asList(new CondicaoMoradiaDTO(new Long(1000), "TESTE"));
		ResultadoListagemCondicaoMoradiaDTO resultadoListagemCondicaoMoradiaDto = new ResultadoListagemCondicaoMoradiaDTO();
		resultadoListagemCondicaoMoradiaDto.efetuadoComSucesso(condicaoMoradiaDto);

		Assert.assertTrue(resultadoListagemCondicaoMoradiaDto.sucesso());
		Assert.assertFalse(resultadoListagemCondicaoMoradiaDto.getObjetosDto().isEmpty());
	}
}
