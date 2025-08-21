package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.espera.StatusEsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;

public class TestesFabricaStatusEspera {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_espera_converte_objeto_de_dominio_para_dto() {
		StatusEspera status = StatusEspera.AGENDADO;

		StatusEsperaDTO statusDTO = new FabricaStatusEspera()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_espera_converte_objeto_de_dto_para_dominio() {
		StatusEspera statusEsperado = StatusEspera.CANCELADO;
		StatusEsperaDTO statusDto = new StatusEsperaDTO(
				statusEsperado.toString());

		StatusEspera statusObtido = new FabricaStatusEspera()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
