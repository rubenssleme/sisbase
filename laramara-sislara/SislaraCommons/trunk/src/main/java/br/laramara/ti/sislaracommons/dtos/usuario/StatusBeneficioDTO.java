package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusBeneficioDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String status;

	public StatusBeneficioDTO(String status) {
		this.status = status;
	}

	public String toString() {
		return status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusBeneficioDTO other = (StatusBeneficioDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
