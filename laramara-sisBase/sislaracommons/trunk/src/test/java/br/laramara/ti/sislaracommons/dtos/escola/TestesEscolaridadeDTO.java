package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEscolaridadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void escolaridadedto_foi_construida_com_sucesso() {
		String escolaridade = "SUPERIOR COMPLETO";
		List<SerieDTO> seriesDto = new ArrayList<>();
		seriesDto.add(new SerieDTO(new Long(1), "1º SERIE"));
		seriesDto.add(new SerieDTO(new Long(2), "2º SERIE"));
		
		EscolaridadeDTO escolaridadeDto = new EscolaridadeDTO(new Long(1), escolaridade, seriesDto);

		Assert.assertEquals(escolaridadeDto.toString(), escolaridade);
		Assert.assertEquals(escolaridadeDto.getSeries().size(), 2);
	}
}
