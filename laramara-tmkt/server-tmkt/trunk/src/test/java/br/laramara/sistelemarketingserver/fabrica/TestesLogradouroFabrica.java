package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoLogradouro;
import br.laramara.sistelemarketingserver.dominio.Logradouro;
import br.laramara.sistelemarketingserver.fabricas.LogradouroFabrica;

public class TestesLogradouroFabrica {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_logradouro_converte_objeto_de_dominio_para_dto() {
		Logradouro logradouro = ContextoLogradouro.fabricarLogradouro();

		LogradouroDTO logradouroDtoCovertido = new LogradouroFabrica().converterParaDTO(logradouro);

		Assert.assertEquals(logradouroDtoCovertido.getCep(), logradouro.getCep());
		Assert.assertEquals(logradouroDtoCovertido.getBairro(), logradouro.getBairro());
		Assert.assertEquals(logradouroDtoCovertido.getDescricao(), logradouro.getDescricao());
		Assert.assertEquals(logradouroDtoCovertido.getMunicipioDto().getId(), logradouro.getMunicipio().getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_logradouro_converte_objeto_dto_para_dominio() {
		LogradouroDTO logradouroDto = ContextoLogradouro.fabricarLogradouroDto();

		Logradouro logradouroCovertido = new LogradouroFabrica().converterParaDominio(logradouroDto);

		Assert.assertEquals(logradouroCovertido.getCep(), logradouroDto.getCep());
		Assert.assertEquals(logradouroCovertido.getBairro(), logradouroDto.getBairro());
		Assert.assertEquals(logradouroCovertido.getDescricao(), logradouroDto.getDescricao());
		Assert.assertEquals(logradouroCovertido.getMunicipio().getId(), logradouroDto.getMunicipioDto().getId());
	}
}
