package br.laramara.sistelemarketingcommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesBairroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void bairro_dto_foi_construida_com_sucesso() {
		String descricao = "São Paulo";
		
		BairroDTO bairroDto = new BairroDTO();
		bairroDto.setNome(descricao);
	
		Assert.assertEquals(bairroDto.getNome(), descricao);
	}
}
