package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EpocaIncidenciaDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String epocaIncidencia;

	public EpocaIncidenciaDTO(String epocaIncidencia) {
		this.epocaIncidencia = epocaIncidencia;
	}

	public String toString() {
		return epocaIncidencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EpocaIncidenciaDTO other = (EpocaIncidenciaDTO) obj;
		if (epocaIncidencia == null) {
			if (other.epocaIncidencia != null)
				return false;
		} else if (!epocaIncidencia.equals(other.epocaIncidencia))
			return false;
		return true;
	}

}
