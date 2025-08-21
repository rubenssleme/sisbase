package br.laramara.sistelemarketingcommons.dtos.campanha;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCriterioResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_resultado_dto_foi_construida_com_erro() {

		CriterioDTO criterioDTO = ContextoCriterioDTO.criarDto();
		String mensagem = "Erro de validação.";

		CriterioResultadoDTO criterioResultadoDTO = new CriterioResultadoDTO();
		criterioResultadoDTO.efetuadoComErro(criterioDTO, mensagem);

		Assert.assertEquals(criterioResultadoDTO.obterCriterioDto().getId(), criterioDTO.getId());
		Assert.assertTrue(criterioResultadoDTO.getMensagem().contains(mensagem));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_resultado_dto_foi_construida_com_sucesso() {

		CriterioDTO criterioDTO = ContextoCriterioDTO.criarDto();

		CriterioResultadoDTO criterioResultadoDTO = new CriterioResultadoDTO();
		criterioResultadoDTO.efetuadoComSucesso(criterioDTO);

		Assert.assertEquals(criterioResultadoDTO.obterCriterioDto().getId(), criterioDTO.getId());
	}
}
