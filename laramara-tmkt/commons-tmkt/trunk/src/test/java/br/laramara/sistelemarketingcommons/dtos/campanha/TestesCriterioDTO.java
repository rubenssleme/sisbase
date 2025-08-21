package br.laramara.sistelemarketingcommons.dtos.campanha;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.ContextoMunicipioDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCriterioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String nome = "Nome do criterio";
		MunicipioDTO municipioDto = ContextoMunicipioDTO.construir();
		String bairro = "Barra Funda";
		Integer quantidadeAAdistribuir = new Integer(1000);

		CriterioDTO criterioDto = new CriterioDTO();
		criterioDto.setId(id);
		criterioDto.setNome(nome);
		criterioDto.setMunicipioDto(municipioDto);
		criterioDto.setBairro(bairro);
		criterioDto.setQuantidadeAAdistribuir(quantidadeAAdistribuir);
		
		Assert.assertEquals(criterioDto.getId(), id);
		Assert.assertEquals(criterioDto.getNome(), nome);
		Assert.assertEquals(criterioDto.getMunicipioDto().getId(), municipioDto.getId());
		Assert.assertEquals(criterioDto.getBairro(), bairro);
		Assert.assertEquals(criterioDto.getQuantidadeAAdistribuir(), quantidadeAAdistribuir);
	}
}
