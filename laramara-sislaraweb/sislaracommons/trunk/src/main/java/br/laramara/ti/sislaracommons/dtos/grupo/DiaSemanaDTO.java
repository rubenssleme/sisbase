package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DiaSemanaDTO extends ModeloDTO {

	private static final long serialVersionUID = 2004430618894014352L;
	
	private String diaSemana;

	public DiaSemanaDTO(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		return diaSemana;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaSemanaDTO other = (DiaSemanaDTO) obj;
		if (diaSemana == null) {
			if (other.diaSemana != null)
				return false;
		} else if (!diaSemana.equals(other.diaSemana))
			return false;
		return true;
	}
}
