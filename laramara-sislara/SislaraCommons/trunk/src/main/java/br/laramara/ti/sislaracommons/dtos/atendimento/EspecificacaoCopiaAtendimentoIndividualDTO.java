package br.laramara.ti.sislaracommons.dtos.atendimento;

import java.io.Serializable;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;

public class EspecificacaoCopiaAtendimentoIndividualDTO implements Serializable {

	private static final long serialVersionUID = -7579768383318396850L;

	private HorarioDTO horarioDto;

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}
}
