package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;
import java.util.Arrays;

import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;

public class ContextoCampanha {

	public static Campanha fabricar() {
		Campanha campanha = new Campanha();
		campanha.setId(new Long(1000));
		campanha.setNome("Nome da campanha");
		campanha.setDataInicio("01/12/2018");
		campanha.setDataTermino("31/12/2018");
		campanha.setDescricao("Grande texto de descricao.");
		campanha.setMetaFinanceira(new BigDecimal("15000.50"));
		campanha.setMetaQuantidadeLigacoes(new Integer(10000));
		campanha.setCriterios(Arrays.asList(ContextoCriterio.fabricar()));
		campanha.setAlocacoesOperadores(Arrays.asList(ContextoAlocacaoOperador.fabricar()));
		return campanha;
	}

	public static CampanhaDTO fabricarDto() {
		CampanhaDTO campanhaDTO = new CampanhaDTO();
		campanhaDTO.setId(new Long(1000));
		campanhaDTO.setNome("Nome da campanha");
		campanhaDTO.setDataInicio("01/12/2018");
		campanhaDTO.setDataTermino("31/12/2018");
		campanhaDTO.setDescricao("Grande texto de descricao.");
		campanhaDTO.setMetaFinanceira(new BigDecimal("15000.50"));
		campanhaDTO.setMetaQuantidadeLigacoes(new Integer(10000));
		campanhaDTO.setCriteriosDto(Arrays.asList(ContextoCriterio.fabricarDto()));
		campanhaDTO.setAlocacoesOperadoresDto(Arrays.asList(ContextoAlocacaoOperador.criarDto()));
		return campanhaDTO;
	}
}
