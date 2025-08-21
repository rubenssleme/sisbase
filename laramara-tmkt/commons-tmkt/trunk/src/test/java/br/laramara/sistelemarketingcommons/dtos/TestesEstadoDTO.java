package br.laramara.sistelemarketingcommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesEstadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void estado_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String descricao = "São Paulo";
		
		EstadoDTO estadoDto = new EstadoDTO();
		estadoDto.setId(id);
		estadoDto.setDescricao(descricao);
	
		Assert.assertEquals(estadoDto.getId(), id);
		Assert.assertEquals(estadoDto.getDescricao(), descricao);
	}
}
