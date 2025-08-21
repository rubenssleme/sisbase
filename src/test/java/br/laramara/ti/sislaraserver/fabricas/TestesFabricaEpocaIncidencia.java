package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;

public class TestesFabricaEpocaIncidencia {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_epoca_incidencia_converte_objeto_de_dominio_para_dto() {
		EpocaIncidencia epocaIncidencia = EpocaIncidencia.ADQUIRIDA;

		EpocaIncidenciaDTO epocaIncidenciaDTO = new FabricaEpocaIncidencia()
				.converterParaDTO(epocaIncidencia);

		Assert.assertEquals(epocaIncidenciaDTO.toString(),
				epocaIncidencia.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_epoca_incidencia_converte_objeto_de_dto_para_dominio() {
		EpocaIncidencia epocaIncidenciaEsperado = EpocaIncidencia.CONGENITA;
		EpocaIncidenciaDTO generoDto = new EpocaIncidenciaDTO(
				epocaIncidenciaEsperado.toString());

		EpocaIncidencia generoObtido = new FabricaEpocaIncidencia()
				.converterParaDominio(generoDto);

		Assert.assertEquals(generoObtido, epocaIncidenciaEsperado);
	}
}
