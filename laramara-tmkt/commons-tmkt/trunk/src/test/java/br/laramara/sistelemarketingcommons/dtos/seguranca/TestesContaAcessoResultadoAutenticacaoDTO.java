package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesContaAcessoResultadoAutenticacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_resultado_autenticacao_dto_foi_construida_com_sucesso() {

		String token = ContextoTokenDTO.criarToken();
		ContaAcessoDTO contaAcessoDTO = ContextoContaAcessoDTO.criar();

		ContaAcessoResultadoAutenticacaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoResultadoAutenticacaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.efetuadoComSucesso(contaAcessoDTO, token);
		
		Assert.assertEquals(contaAcessoSolicitacaoEdicaoDTO.getToken(), token);
		Assert.assertEquals(contaAcessoSolicitacaoEdicaoDTO.obterContaAcessoDto(), contaAcessoDTO);
	}
}
