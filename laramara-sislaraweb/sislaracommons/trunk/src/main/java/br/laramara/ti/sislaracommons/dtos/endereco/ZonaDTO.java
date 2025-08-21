package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ZonaDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;
	
	private String zona;

	public ZonaDTO(String zona) {
		this.zona = zona;
	}

	public String toString() {
		return zona;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZonaDTO other = (ZonaDTO) obj;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}
}
