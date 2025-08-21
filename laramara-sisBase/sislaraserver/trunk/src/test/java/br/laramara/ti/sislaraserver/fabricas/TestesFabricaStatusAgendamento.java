package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.StatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.StatusAgendamento;

public class TestesFabricaStatusAgendamento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_agendamento_converte_objeto_de_dominio_para_dto() {
		StatusAgendamento status = StatusAgendamento.AGENDADO;

		StatusAgendamentoDTO statusDTO = new FabricaStatusAgendamento()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_agendamento_converte_objeto_de_dto_para_dominio() {
		StatusAgendamento statusEsperado = StatusAgendamento.CANCELADO;
		StatusAgendamentoDTO statusDto = new StatusAgendamentoDTO(
				statusEsperado.toString());

		StatusAgendamento statusObtido = new FabricaStatusAgendamento()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
