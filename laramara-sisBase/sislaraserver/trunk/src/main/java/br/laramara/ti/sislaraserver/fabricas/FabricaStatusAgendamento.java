package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.StatusAgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.StatusAgendamento;

public class FabricaStatusAgendamento extends
		FabricaBase<StatusAgendamentoDTO, StatusAgendamento> {
	public final StatusAgendamentoDTO converterParaDTO(
			StatusAgendamento status) {
		return status != null ? new StatusAgendamentoDTO(status.toString())
				: null;
	}

	public final StatusAgendamento converterParaDominio(
			StatusAgendamentoDTO status) {
		return status != null ? StatusAgendamento.valueOf(
				StatusAgendamento.class, status.toString()) : null;
	}
}
