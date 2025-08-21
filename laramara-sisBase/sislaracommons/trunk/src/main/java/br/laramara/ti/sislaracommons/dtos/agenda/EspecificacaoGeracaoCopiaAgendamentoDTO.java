package br.laramara.ti.sislaracommons.dtos.agenda;

import java.io.Serializable;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;

public class EspecificacaoGeracaoCopiaAgendamentoDTO implements Serializable {

	private static final long serialVersionUID = 6127343287988660867L;

	private String data;
	private HorarioDTO horarioDto;

	public EspecificacaoGeracaoCopiaAgendamentoDTO() {
		this.horarioDto = new HorarioDTO();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}
}
