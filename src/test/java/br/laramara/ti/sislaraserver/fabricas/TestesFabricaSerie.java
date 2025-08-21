package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.Serie;

public class TestesFabricaSerie {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_serie_converte_objeto_de_dominio_para_dto() {
		Serie serie = new Serie(new Long(1), "1º Série");

		SerieDTO serieDTO = new FabricaSerie().converterParaDTO(serie);

		Assert.assertEquals(serie.getId(), serieDTO.getId());
		Assert.assertEquals(serie.getDescricao(), serieDTO.toString());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_serie_converte_objeto_de_dto_para_dominio() {
		SerieDTO serieDto = new SerieDTO(new Long(1), "1º Série");

		Serie serie = new FabricaSerie().converterParaDominio(serieDto);

		Assert.assertEquals(serie.getId(), serieDto.getId());
		Assert.assertEquals(serie.getDescricao(), serieDto.toString());
	}
}
