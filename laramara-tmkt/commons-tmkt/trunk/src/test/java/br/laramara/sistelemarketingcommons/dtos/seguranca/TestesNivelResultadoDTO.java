package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesNivelResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_resultado_dto_foi_construida_com_sucesso() {
	
		NivelDTO nivelDTO  = ContextoNivelDTO.construir();
		
		NivelResultadoDTO nivelResultadoDTO = new NivelResultadoDTO();
		nivelResultadoDTO.efetuadoComSucesso(nivelDTO);

		Assert.assertEquals(nivelResultadoDTO.obterUnico(), nivelDTO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_resultado_listagem_dto_foi_construida_com_sucesso() {
	
		List<NivelDTO> niveisDTO  = Arrays.asList(ContextoNivelDTO.construir());
		
		NivelResultadoDTO nivelResultadoDTO = new NivelResultadoDTO();
		nivelResultadoDTO.efetuadoComSucesso(niveisDTO);

		Assert.assertEquals(nivelResultadoDTO.getNiveisDto().size(),
				nivelResultadoDTO.getNiveisDto().size());
	}
}
