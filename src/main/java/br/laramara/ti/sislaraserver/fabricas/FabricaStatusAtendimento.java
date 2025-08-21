package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.StatusAtendimento;

public class FabricaStatusAtendimento extends
		FabricaBase<StatusAtendimentoDTO, StatusAtendimento> {
	public final StatusAtendimentoDTO converterParaDTO(
			StatusAtendimento status) {
		return status != null ? new StatusAtendimentoDTO(status.toString())
				: null;
	}

	public final StatusAtendimento converterParaDominio(
			StatusAtendimentoDTO status) {
		return status != null ? StatusAtendimento.valueOf(
				StatusAtendimento.class, status.toString()) : null;
	}
}
