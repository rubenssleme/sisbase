package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusDemandaDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;

	private String statusDemanda;

	public StatusDemandaDTO(String statusDemanda) {
		this.statusDemanda = statusDemanda;
	}

	public String toString() {
		return statusDemanda;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusDemandaDTO other = (StatusDemandaDTO) obj;
		if (statusDemanda == null) {
			if (other.statusDemanda != null)
				return false;
		} else if (!statusDemanda.equals(other.statusDemanda))
			return false;
		return true;
	}
}
