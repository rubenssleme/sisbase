package br.laramara.ti.sislaracommons.dtos.retirada;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;

public class RetiradaDTO extends ModeloDTO{

	private static final long serialVersionUID = -2693891162923070277L;

	private Long prontuario;

	private ProfissionalDTO profissionalDto;

	private ProfissionalDTO voluntarioDto;

	public Long getProntuario() {
		return prontuario;
	}

	public void setProntuario(Long prontuario) {
		this.prontuario = prontuario;
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
	}

	public ProfissionalDTO getVoluntarioDto() {
		return voluntarioDto;
	}

	public void setVoluntarioDto(ProfissionalDTO voluntarioDto) {
		this.voluntarioDto = voluntarioDto;
	}
}
