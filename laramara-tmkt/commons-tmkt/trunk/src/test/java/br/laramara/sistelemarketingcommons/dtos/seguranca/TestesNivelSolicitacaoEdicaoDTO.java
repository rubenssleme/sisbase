package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesNivelSolicitacaoEdicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_solicitacao_edicao_dto_foi_construida_com_sucesso() {

		String token = ContextoTokenDTO.criarToken();
		NivelDTO nivelDTO = ContextoNivelDTO.construir();

		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDTO = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDTO.setToken(token);
		nivelSolicitacaoEdicaoDTO.setNivelDto(nivelDTO);

		Assert.assertEquals(nivelSolicitacaoEdicaoDTO.getToken(), token);
		Assert.assertEquals(nivelSolicitacaoEdicaoDTO.getNivelDto(), nivelDTO);
	}
}
