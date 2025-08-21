package br.laramara.sistelemarketingcommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesMunicipioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void municipio_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String descricao = "São Paulo";
		
		MunicipioDTO municipioDto = new MunicipioDTO();
		municipioDto.setId(id);
		municipioDto.setDescricao(descricao);
	
		Assert.assertEquals(municipioDto.getId(), id);
		Assert.assertEquals(municipioDto.getDescricao(), descricao);
	}
}
