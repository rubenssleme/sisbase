package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

public class TestesFabricaMunicipio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_municipio_converte_objeto_de_dominio_para_dto() {
		Municipio municipio = new Municipio(new Long(4850), "São Paulo", UF.SP);

		MunicipioDTO municipioDTO = new FabricaMunicipio()
				.converterParaDTO(municipio);

		Assert.assertEquals(municipio.getId(), municipioDTO.getId());
		Assert.assertEquals(municipio.getNome(), municipioDTO.toString());
		Assert.assertEquals(municipio.getUf().toString(), municipioDTO.getUf()
				.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_municipio_converte_objeto_de_dto_para_domino() {
		MunicipioDTO municipioDto = new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO("SP"));

		Municipio municipio = new FabricaMunicipio()
				.converterParaDominio(municipioDto);

		Assert.assertEquals(municipio.getId(), municipioDto.getId());
		Assert.assertEquals(municipio.getNome(), municipioDto.toString());
		Assert.assertEquals(municipio.getUf().toString(), municipioDto.getUf()
				.toString());
	}
}
