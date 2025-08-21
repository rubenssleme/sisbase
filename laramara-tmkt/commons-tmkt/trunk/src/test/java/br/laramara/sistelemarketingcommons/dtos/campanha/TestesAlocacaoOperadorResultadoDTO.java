package br.laramara.sistelemarketingcommons.dtos.campanha;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesAlocacaoOperadorResultadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void alocacao_operador_resultado_dto_foi_construida_com_erro() {

		AlocacaoOperadorDTO alocacaoOperadorDTO = ContextoAlocacaoOperadorDTO.criar();
		String mensagem = "Erro de validação.";

		AlocacaoOperadorResultadoDTO alocacaoOperadorResultadoDTO = new AlocacaoOperadorResultadoDTO();
		alocacaoOperadorResultadoDTO.efetuadoComErro(alocacaoOperadorDTO, mensagem);

		Assert.assertEquals(alocacaoOperadorResultadoDTO.obterAlocacaoOperadorDto().getId(),
				alocacaoOperadorDTO.getId());
		Assert.assertTrue(alocacaoOperadorResultadoDTO.getMensagem().contains(mensagem));
	}
}
