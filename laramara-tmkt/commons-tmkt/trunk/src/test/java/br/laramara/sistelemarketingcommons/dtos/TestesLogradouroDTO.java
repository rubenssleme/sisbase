package br.laramara.sistelemarketingcommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesLogradouroDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void logradouro_dto_foi_construida_com_sucesso() {
		String cep = "01151000";
		String descricao = "Rua Brigadeiro Galvão";
		String bairro = "Barra Funda";
		MunicipioDTO municipioDTO = new MunicipioDTO();

		LogradouroDTO logradouroDto = new LogradouroDTO();
		logradouroDto.setCep(cep);
		logradouroDto.setDescricao(descricao);
		logradouroDto.setBairro(bairro);
		logradouroDto.setMunicipioDto(municipioDTO);

		Assert.assertEquals(logradouroDto.getCep(), cep);
		Assert.assertEquals(logradouroDto.getDescricao(), descricao);
		Assert.assertEquals(logradouroDto.getBairro(), bairro);
		Assert.assertEquals(logradouroDto.getMunicipioDto().getId(), municipioDTO.getId());
	}
}
