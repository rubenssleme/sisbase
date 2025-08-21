package br.laramara.ti.sislaracommons.dtos.agenda;

import java.io.Serializable;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;

public class EspecificacaoAssociacaoAgendamentoDTO implements Serializable {

	private static final long serialVersionUID = -8681712919672547712L;

	private EsperaDTO esperaDto;
	private AgendamentoDTO agendamentoDto;

	public EsperaDTO getEsperaDto() {
		return esperaDto;
	}

	public void setEsperaDto(EsperaDTO esperaDto) {
		this.esperaDto = esperaDto;
	}

	public AgendamentoDTO getAgendamentoDto() {
		return agendamentoDto;
	}

	public void setAgendamentoDto(AgendamentoDTO agendamentoDto) {
		this.agendamentoDto = agendamentoDto;
	}
}
