package br.laramara.sistelemarketingcommons.dtos;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesEstadoResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void estado_resultado_listagem_dto_foi_construida_com_sucesso() {
	
		List<EstadoDTO> estadosDTO  = Arrays.asList(ContextoEstadoDTO.construir());
		
		EstadoResultadoDTO estadoResultadoDTO = new EstadoResultadoDTO();
		estadoResultadoDTO.efetuadoComSucesso(estadosDTO);

		Assert.assertEquals(estadoResultadoDTO.getEstadosDto().size(),
				estadoResultadoDTO.getEstadosDto().size());
	}
}
