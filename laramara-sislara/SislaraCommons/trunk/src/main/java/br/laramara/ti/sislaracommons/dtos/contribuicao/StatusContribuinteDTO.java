package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusContribuinteDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String status;

	public StatusContribuinteDTO(String status) {
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
		StatusContribuinteDTO other = (StatusContribuinteDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
