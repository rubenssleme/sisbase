package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;

public class ContextoEspecificacaoGeracaoCopiaAgendamento {

	public static EspecificacaoGeracaoCopiaAgendamentoDTO fabricarComTodosOsDados() {
		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDto = new EspecificacaoGeracaoCopiaAgendamentoDTO();
		especificacaoGeracaoCopiaAgendamentoDto.setData("31/01/2099");
		especificacaoGeracaoCopiaAgendamentoDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		return especificacaoGeracaoCopiaAgendamentoDto;
	}
}
