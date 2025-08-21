package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;

public class CampanhaFabrica extends FabricaRecursiva<CampanhaDTO, Campanha> {
	public final CampanhaDTO converterParaDTO(Campanha campanha) {
		CampanhaDTO campanhaDto = new CampanhaDTO();
		if (campanha != null) {
			campanhaDto.setId(campanha.getId());
			campanhaDto.setNome(campanha.getNome());
			campanhaDto.setDescricao(campanha.getDescricao());
			campanhaDto.setDataInicio(campanha.getDataInicio());
			campanhaDto.setDataTermino(campanha.getDataTermino());
			campanhaDto.setMetaFinanceira(campanha.getMetaFinanceira());
			campanhaDto.setMetaQuantidadeLigacoes(campanha.getMetaQuantidadeLigacoes());
			campanhaDto.setCriteriosDto(new CriterioFabrica().converterParaDTO(campanha.getCriterios()));
			campanhaDto.setAlocacoesOperadoresDto(
					new AlocacaoOperadorFabrica().converterParaDTO(campanha.getAlocacoesOperadores()));
		}
		return campanhaDto;
	}

	@Override
	public Campanha converterParaDominio(CampanhaDTO campanhaDto, Campanha campanha) {
		if (campanhaDto != null) {
			if (campanha == null) {
				campanha = obterNovo();
			}
			campanha.setNome(campanhaDto.getNome());
			campanha.setDescricao(campanhaDto.getDescricao());
			campanha.setDataInicio(campanhaDto.getDataInicio());
			campanha.setDataTermino(campanhaDto.getDataTermino());
			campanha.setMetaFinanceira(campanhaDto.getMetaFinanceira());
			campanha.setMetaQuantidadeLigacoes(campanhaDto.getMetaQuantidadeLigacoes());
			campanha.setCriterios(new CriterioFabrica().converterParaDominio(campanhaDto.getCriteriosDto()));
			campanha.setAlocacoesOperadores(
					new AlocacaoOperadorFabrica().converterParaDominio(campanhaDto.getAlocacoesOperadoresDto()));
		}
		return campanha;
	}

	@Override
	public Campanha obterNovo() {
		return new Campanha();
	}
}
