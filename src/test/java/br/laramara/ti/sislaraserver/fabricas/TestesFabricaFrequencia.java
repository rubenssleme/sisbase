package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;

public class TestesFabricaFrequencia {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_frequencia_converte_objeto_de_dominio_para_dto() {
		Frequencia frequencia = Frequencia.AT;

		FrequenciaDTO frequenciaDTO = new FabricaFrequencia()
				.converterParaDTO(frequencia);

		Assert.assertEquals(frequenciaDTO.toString(), frequencia.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_frequencia_converte_objeto_de_dto_para_dominio() {
		Frequencia frequenciaEsperada = Frequencia.AT;
		FrequenciaDTO frequenciaDto = new FrequenciaDTO(
				frequenciaEsperada.toString());

		Frequencia frequenciaObtido = new FabricaFrequencia()
				.converterParaDominio(frequenciaDto);

		Assert.assertEquals(frequenciaObtido, frequenciaEsperada);
	}
}
