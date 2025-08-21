package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoApoioDTO extends ModeloDTO {

	private static final long serialVersionUID = -4073711691676623559L;

	private String tipoApoio;

	public TipoApoioDTO(String tipoApoio) {
		this.tipoApoio = tipoApoio;
	}

	public String toString() {
		return tipoApoio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoApoioDTO other = (TipoApoioDTO) obj;
		if (tipoApoio == null) {
			if (other.tipoApoio != null)
				return false;
		} else if (!tipoApoio.equals(other.tipoApoio))
			return false;
		return true;
	}

}
