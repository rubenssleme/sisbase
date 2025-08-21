package br.laramara.ti.sislaracommons.dtos.espera;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoEsperaDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;

	private String tipoEspera;

	public TipoEsperaDTO(String tipoEspera) {
		this.tipoEspera = tipoEspera;
	}

	public String toString() {
		return tipoEspera;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoEsperaDTO other = (TipoEsperaDTO) obj;
		if (tipoEspera == null) {
			if (other.tipoEspera != null)
				return false;
		} else if (!tipoEspera.equals(other.tipoEspera))
			return false;
		return true;
	}
}
