package br.laramara.ti.sislaracommons.dtos.espera;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusEsperaDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;

	private String statusEspera;

	public StatusEsperaDTO(String statusEspera) {
		this.statusEspera = statusEspera;
	}

	public String toString() {
		return statusEspera;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusEsperaDTO other = (StatusEsperaDTO) obj;
		if (statusEspera == null) {
			if (other.statusEspera != null)
				return false;
		} else if (!statusEspera.equals(other.statusEspera))
			return false;
		return true;
	}
}
