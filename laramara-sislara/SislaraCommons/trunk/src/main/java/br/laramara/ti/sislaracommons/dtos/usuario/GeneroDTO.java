package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class GeneroDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;
	
	private String genero;

	public GeneroDTO(String genero) {
		this.genero = genero;
	}

	public String toString() {
		return genero;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneroDTO other = (GeneroDTO) obj;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		return true;
	}
}
