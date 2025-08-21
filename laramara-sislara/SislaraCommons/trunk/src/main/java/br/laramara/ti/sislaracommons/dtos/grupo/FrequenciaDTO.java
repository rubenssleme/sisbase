package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class FrequenciaDTO extends ModeloDTO {

	private static final long serialVersionUID = 2004430618894014352L;

	private String frequencia;

	public FrequenciaDTO(String frequencia) {
		this.frequencia = frequencia;
	}

	@Override
	public String toString() {
		return frequencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrequenciaDTO other = (FrequenciaDTO) obj;
		if (frequencia == null) {
			if (other.frequencia != null)
				return false;
		} else if (!frequencia.equals(other.frequencia))
			return false;
		return true;
	}
}
