package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusRelacaoComModuloDTO extends ModeloDTO {

	private static final long serialVersionUID = 2004430618894014352L;

	private String status;

	public StatusRelacaoComModuloDTO(String status) {
		this.status = status;
	}

	@Override
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
		StatusRelacaoComModuloDTO other = (StatusRelacaoComModuloDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
