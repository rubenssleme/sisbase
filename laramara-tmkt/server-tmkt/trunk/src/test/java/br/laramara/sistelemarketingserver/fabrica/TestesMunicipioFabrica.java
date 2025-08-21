package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoMunicipio;
import br.laramara.sistelemarketingserver.dominio.Municipio;
import br.laramara.sistelemarketingserver.fabricas.MunicipioFabrica;

public class TestesMunicipioFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_municipios_converte_objeto_de_dominio_para_dto() {
		Municipio municipio = ContextoMunicipio.fabricarMunicipio();

		MunicipioDTO municipioDtoCovertido = new MunicipioFabrica().converterParaDTO(municipio);

		Assert.assertEquals(municipioDtoCovertido.getId(), municipio.getId());
		Assert.assertEquals(municipioDtoCovertido.getDescricao(), municipio.getDescricao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_municipio_converte_objeto_dto_para_dominio() {
		MunicipioDTO municipoDto = ContextoMunicipio.fabricarMunicipioDto();

		Municipio municipioCovertido = new MunicipioFabrica().converterParaDominio(municipoDto);

		Assert.assertEquals(municipioCovertido.getId(), municipoDto.getId());
		Assert.assertEquals(municipioCovertido.getDescricao(), municipoDto.getDescricao());
	}
}
