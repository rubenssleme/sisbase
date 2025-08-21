package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;

public class RecursoRelacionamentoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -7530827602087153565L;

	private Long id;
	private RecursoDTO recursoDto;
	private PossuiNecessitaDTO possuiNecessitaDto;

	public RecursoRelacionamentoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RecursoDTO getRecursoDto() {
		return recursoDto;
	}

	public void setRecursoDto(RecursoDTO recursoDto) {
		this.recursoDto = recursoDto;
	}

	public PossuiNecessitaDTO getPossuiNecessitaDto() {
		return possuiNecessitaDto;
	}

	public void setPossuiNecessitaDto(PossuiNecessitaDTO possuiNecessitaDto) {
		this.possuiNecessitaDto = possuiNecessitaDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoRelacionamentoDTO other = (RecursoRelacionamentoDTO) obj;
		if (recursoDto == null) {
			if (other.recursoDto != null)
				return false;
		} else if (!recursoDto.equals(other.recursoDto))
			return false;
		if (possuiNecessitaDto == null) {
			if (other.possuiNecessitaDto != null)
				return false;
		} else if (!possuiNecessitaDto.equals(other.possuiNecessitaDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return recursoDto.toString() + " - " + possuiNecessitaDto.toString();
	}
}
