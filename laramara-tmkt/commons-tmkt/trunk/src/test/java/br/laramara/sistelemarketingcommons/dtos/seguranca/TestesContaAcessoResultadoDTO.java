package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesContaAcessoResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_resultado_dto_foi_construida_com_sucesso() {
	
		ContaAcessoDTO contaAcessoDTO  = ContextoContaAcessoDTO.criar();
		
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		contaAcessoResultadoDTO.efetuadoComSucesso(contaAcessoDTO);

		Assert.assertEquals(contaAcessoResultadoDTO.obterUnico(), contaAcessoDTO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_resultado_listagem_dto_foi_construida_com_sucesso() {
	
		List<ContaAcessoDTO> contaAcessoDTO  = Arrays.asList(ContextoContaAcessoDTO.criar());
		
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		contaAcessoResultadoDTO.efetuadoComSucesso(contaAcessoDTO);

		Assert.assertEquals(contaAcessoResultadoDTO.getContasAcessosDto().size(),
				contaAcessoResultadoDTO.getContasAcessosDto().size());
	}
}
