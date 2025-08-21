package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoCriterio;
import br.laramara.sistelemarketingserver.dominio.campanha.Criterio;
import br.laramara.sistelemarketingserver.fabricas.CriterioFabrica;

public class TestesCriterioFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_criterio_converte_objeto_de_dominio_para_dto() {
		Criterio criterio = ContextoCriterio.fabricar();

		CriterioDTO criterioDtoCovertido = new CriterioFabrica().converterParaDTO(criterio);

		Assert.assertEquals(criterioDtoCovertido.getId(), criterio.getId());
		Assert.assertEquals(criterioDtoCovertido.getNome(), criterio.getNome());
		Assert.assertEquals(criterioDtoCovertido.getMunicipioDto().getId(), criterio.getMunicipio().getId());
		Assert.assertEquals(criterioDtoCovertido.getBairro(), criterio.getBairro());
		Assert.assertEquals(criterioDtoCovertido.getQuantidadeAAdistribuir(), criterio.getQuantidadeDistribuir());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_criterio_converte_objeto_dto_para_dominio() {
		CriterioDTO criterioDto = ContextoCriterio.fabricarDto();

		Criterio campanhaCovertido = new CriterioFabrica().converterParaDominio(criterioDto);

		Assert.assertEquals(campanhaCovertido.getId(), criterioDto.getId());
		Assert.assertEquals(campanhaCovertido.getNome(), criterioDto.getNome());
		Assert.assertEquals(campanhaCovertido.getMunicipio().getId(), criterioDto.getMunicipioDto().getId());
		Assert.assertEquals(campanhaCovertido.getBairro(), criterioDto.getBairro());
		Assert.assertEquals(campanhaCovertido.getQuantidadeDistribuir(), criterioDto.getQuantidadeAAdistribuir());
	}
}
