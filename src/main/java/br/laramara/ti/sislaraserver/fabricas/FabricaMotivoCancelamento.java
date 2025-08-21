package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;

public class FabricaMotivoCancelamento extends
		FabricaBase<MotivoCancelamentoDTO, MotivoCancelamento> {
	public final MotivoCancelamentoDTO converterParaDTO(
			MotivoCancelamento motivoCancelamento) {
		return motivoCancelamento != null ? new MotivoCancelamentoDTO(
				motivoCancelamento.getId(), motivoCancelamento.getDescricao())
				: null;
	}

	public final MotivoCancelamento converterParaDominio(
			MotivoCancelamentoDTO motivoCancelamentoDto) {
		return motivoCancelamentoDto != null ? new MotivoCancelamento(
				motivoCancelamentoDto.getId(), motivoCancelamentoDto.toString())
				: null;
	}
}
