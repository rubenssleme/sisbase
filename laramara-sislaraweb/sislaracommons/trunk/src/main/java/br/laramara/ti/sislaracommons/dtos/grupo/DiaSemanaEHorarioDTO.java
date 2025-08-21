package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DiaSemanaEHorarioDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -42929459273497408L;

	private Long id;
	private DiaSemanaDTO diaSemanaDto;
	private HorarioDTO horarioDto;

	public DiaSemanaEHorarioDTO() {
		horarioDto = new HorarioDTO();
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiaSemanaDTO getDiaSemanaDto() {
		return diaSemanaDto;
	}

	public void setDiaSemanaDto(DiaSemanaDTO diaSemanaDto) {
		this.diaSemanaDto = diaSemanaDto;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaSemanaEHorarioDTO other = (DiaSemanaEHorarioDTO) obj;
		if (diaSemanaDto == null) {
			if (other.diaSemanaDto != null)
				return false;
		} else if (!diaSemanaDto.equals(other.diaSemanaDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + diaSemanaDto + ", " + horarioDto + "]";
	}
}
