package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCampanhaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String nome = "Nome da campanha";
		String data = "01/01/2018";
		String descricao = "Texto grande.";
		BigDecimal valor = new BigDecimal("1500.50");
		Integer metaQuantidadeLigacoes = new Integer("123");
		CriterioDTO criterioDTO = new CriterioDTO();
		criterioDTO.setNome(nome);
		List<CriterioDTO> criteriosDto = Arrays.asList(ContextoCriterioDTO.criarDto());

		CampanhaDTO campanhaDto = new CampanhaDTO();
		campanhaDto.setId(id);
		campanhaDto.setNome(nome);
		campanhaDto.setDescricao(descricao);
		campanhaDto.setDataInicio(data);
		campanhaDto.setDataTermino(data);
		campanhaDto.setMetaFinanceira(valor);
		campanhaDto.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		campanhaDto.setCriteriosDto(criteriosDto);

		Assert.assertEquals(campanhaDto.getId(), id);
		Assert.assertEquals(campanhaDto.getNome(), nome);
		Assert.assertEquals(campanhaDto.getDescricao(), descricao);
		Assert.assertEquals(campanhaDto.getDataInicio(), data);
		Assert.assertEquals(campanhaDto.getDataTermino(), data);
		Assert.assertEquals(campanhaDto.getMetaFinanceira(), valor);
		Assert.assertEquals(campanhaDto.getMetaQuantidadeLigacoes(), metaQuantidadeLigacoes);
		Assert.assertEquals(campanhaDto.getCriteriosDto(), criteriosDto);
	}
}
