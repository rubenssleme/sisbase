package br.laramara.sistelemarketingcommons.dtos.campanha;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContextoTokenDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCampanhaSolicitacaoEdicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_solicitacao_edicao_dto_foi_construida_com_sucesso() {

		String token = ContextoTokenDTO.criarToken();
		CampanhaDTO campanhaDTO = ContextoCampanhaDTO.construir();

		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDTO = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDTO.setToken(token);
		campanhaSolicitacaoEdicaoDTO.setCampanhaDto(campanhaDTO);

		Assert.assertEquals(campanhaSolicitacaoEdicaoDTO.getToken(), token);
		Assert.assertEquals(campanhaSolicitacaoEdicaoDTO.getCampanhaDto(), campanhaDTO);
	}
}
