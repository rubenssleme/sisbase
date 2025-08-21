package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoCampanha;
import br.laramara.sistelemarketingserver.fabricas.CampanhaFabrica;

public class TestesCampanhaFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_campanha_converte_objeto_de_dominio_para_dto() {
		Campanha campanha = ContextoCampanha.fabricar();

		CampanhaDTO camapanhaDtoCovertido = new CampanhaFabrica().converterParaDTO(campanha);

		Assert.assertEquals(camapanhaDtoCovertido.getId(), campanha.getId());
		Assert.assertEquals(camapanhaDtoCovertido.getNome(), campanha.getNome());
		Assert.assertEquals(camapanhaDtoCovertido.getDescricao(), campanha.getDescricao());
		Assert.assertEquals(camapanhaDtoCovertido.getDataInicio(), campanha.getDataInicio());
		Assert.assertEquals(camapanhaDtoCovertido.getDataTermino(), campanha.getDataTermino());
		Assert.assertEquals(camapanhaDtoCovertido.getMetaFinanceira(), campanha.getMetaFinanceira());
		Assert.assertEquals(camapanhaDtoCovertido.getMetaQuantidadeLigacoes(), campanha.getMetaQuantidadeLigacoes());
		Assert.assertEquals(camapanhaDtoCovertido.getCriteriosDto().size(), campanha.getCriterios().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_campanha_converte_objeto_dto_para_dominio() {
		CampanhaDTO campanhaDto = ContextoCampanha.fabricarDto();

		Campanha campanhaCovertido = new CampanhaFabrica().converterParaDominio(campanhaDto);

		Assert.assertEquals(campanhaCovertido.getNome(), campanhaDto.getNome());
		Assert.assertEquals(campanhaCovertido.getDescricao(), campanhaDto.getDescricao());
		Assert.assertEquals(campanhaCovertido.getDataInicio(), campanhaDto.getDataInicio());
		Assert.assertEquals(campanhaCovertido.getDataTermino(), campanhaDto.getDataTermino());
		Assert.assertEquals(campanhaCovertido.getMetaFinanceira(), campanhaDto.getMetaFinanceira());
		Assert.assertEquals(campanhaCovertido.getMetaQuantidadeLigacoes(), campanhaDto.getMetaQuantidadeLigacoes());
		Assert.assertEquals(campanhaCovertido.getCriterios().size(), campanhaDto.getCriteriosDto().size());
	}
}
