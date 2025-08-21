package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoCopiaAgendamento;

public class FabricaEspecificacaoGeracaoCopiaAgendamento
		extends
		FabricaBase<EspecificacaoGeracaoCopiaAgendamentoDTO, EspecificacaoGeracaoCopiaAgendamento> {

	@Override
	public EspecificacaoGeracaoCopiaAgendamentoDTO converterParaDTO(
			EspecificacaoGeracaoCopiaAgendamento especificacaoGeracaoCopiaAgendamento) {
		return null;
	}

	@Override
	public EspecificacaoGeracaoCopiaAgendamento converterParaDominio(
			EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDto) {
		EspecificacaoGeracaoCopiaAgendamento especificacaoGeracaoCopiaAgendamento = new EspecificacaoGeracaoCopiaAgendamento();
		especificacaoGeracaoCopiaAgendamento
				.setData(especificacaoGeracaoCopiaAgendamentoDto.getData());
		especificacaoGeracaoCopiaAgendamento.setHorario(new FabricaHorario()
				.converterParaDominio(especificacaoGeracaoCopiaAgendamentoDto
						.getHorarioDto()));
		return especificacaoGeracaoCopiaAgendamento;
	}
}
