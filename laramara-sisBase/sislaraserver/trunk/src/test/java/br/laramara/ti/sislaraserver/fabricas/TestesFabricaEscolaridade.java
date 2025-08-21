package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.Serie;

public class TestesFabricaEscolaridade {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_escolaridade_converte_objeto_de_dominio_para_dto() {
		List<Serie> series = new ArrayList<>();
		series.add(new Serie(new Long(1), "1º SERIE"));
		series.add(new Serie(new Long(2), "2º SERIE"));
		
		Escolaridade escolaridade = new Escolaridade(new Long(1), "INFANTIL");
		escolaridade.setSeries(series);

		EscolaridadeDTO escolaridadeDTO = new FabricaEscolaridade()
				.converterParaDTO(escolaridade);

		Assert.assertEquals(escolaridadeDTO.getId(), escolaridade.getId());
		Assert.assertEquals(escolaridadeDTO.toString(),
				escolaridade.getDescricao());
		Assert.assertEquals(escolaridadeDTO.getSeries().size(), escolaridade.getSeries().size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_escolaridade_converte_objeto_de_dto_para_dominio() {
	
		List<SerieDTO> seriesDto = new ArrayList<>();
		seriesDto.add(new SerieDTO(new Long(1), "1º Serie"));
		EscolaridadeDTO escolaridadeDto = new EscolaridadeDTO(new Long(1),
				"Fundamental" , seriesDto);

		Escolaridade escolaridadeObtido = new FabricaEscolaridade()
				.converterParaDominio(escolaridadeDto);

		Assert.assertEquals(escolaridadeObtido.getId(), escolaridadeDto.getId());
		Assert.assertEquals(escolaridadeObtido.getDescricao(),
				escolaridadeDto.toString());
		Assert.assertEquals(escolaridadeObtido.getSeries().size(), seriesDto.size());
	}
}
