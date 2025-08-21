package br.laramara.sistelemarketingcommons.dtos.contato;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesDistribuicaoContatoResultadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void distribuicao_contato_resultado_dto_foi_construida_com_erro() {

		String mensagem = "Erro durante obtenção de distribuicao.";

		DistribuicaoContatoResultadoDTO distribuicaoContatoResultadoDTO = new DistribuicaoContatoResultadoDTO();
		distribuicaoContatoResultadoDTO.adicionarErro(mensagem);

		Assert.assertTrue(distribuicaoContatoResultadoDTO.getMensagem().contains(mensagem));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void distribuicao_contato_resultado_dto_foi_construida_com_sucesso() {

		DistribuicaoContatoDTO distribuicaoContatoDTO = ContextoDistribuicaoContatoDTO.criar();

		DistribuicaoContatoResultadoDTO distribuicaoContaoResultadoDTO = new DistribuicaoContatoResultadoDTO();
		distribuicaoContaoResultadoDTO.efetuadoComSucesso(distribuicaoContatoDTO);

		Assert.assertTrue(distribuicaoContaoResultadoDTO.sucesso());
		Assert.assertEquals(distribuicaoContaoResultadoDTO.obterDistribuicaoContatoDto().getContatoDto().getId(),
				distribuicaoContatoDTO.getContatoDto().getId());
	}
}
