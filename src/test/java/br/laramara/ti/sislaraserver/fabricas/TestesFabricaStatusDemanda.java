package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusDemanda;

public class TestesFabricaStatusDemanda {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_demanda_converte_objeto_de_dominio_para_dto() {
		StatusDemanda status = StatusDemanda.AGUARDANDO;

		StatusDemandaDTO statusDTO = new FabricaStatusDemanda()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_demanda_converte_objeto_de_dto_para_dominio() {
		StatusDemanda statusEsperado = StatusDemanda.ENTREGUE;
		StatusDemandaDTO statusDto = new StatusDemandaDTO(
				statusEsperado.toString());

		StatusDemanda statusObtido = new FabricaStatusDemanda()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
