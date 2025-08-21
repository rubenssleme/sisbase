package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesContaAcessoSolicitacaoEdicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_solicitacao_edicao_dto_foi_construida_com_sucesso() {

		String token = ContextoTokenDTO.criarToken();
		ContaAcessoDTO contaAcessoDTO = ContextoContaAcessoDTO.criar();

		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.setToken(token);
		contaAcessoSolicitacaoEdicaoDTO.setContaAcessoDto(contaAcessoDTO);

		Assert.assertEquals(contaAcessoSolicitacaoEdicaoDTO.getToken(), token);
		Assert.assertEquals(contaAcessoSolicitacaoEdicaoDTO.getContaAcessoDto(), contaAcessoDTO);
	}
}
