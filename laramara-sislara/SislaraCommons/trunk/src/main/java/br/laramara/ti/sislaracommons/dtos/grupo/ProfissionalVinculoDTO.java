package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ProfissionalVinculoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 1733813073944488152L;

	private Long id;
	private ProfissionalDTO profissionalDto;
	private boolean participante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
	}

	public boolean isParticipante() {
		return participante;
	}

	public void setParticipante(boolean participante) {
		this.participante = participante;
	}

	@Override
	public String toString() {
		return profissionalDto.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfissionalVinculoDTO other = (ProfissionalVinculoDTO) obj;
		if (profissionalDto == null) {
			if (other.profissionalDto != null)
				return false;
		} else if (!profissionalDto.equals(other.profissionalDto))
			return false;
		return true;
	}
}
