package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EstadoCivilDTO extends ModeloDTO {
	
	private static final long serialVersionUID = 6438856195011725784L;
	
	private Long id;
	private String estadoCivil;

	public EstadoCivilDTO(Long id, String estadoCivil) {
		this.id = id;
		this.estadoCivil = estadoCivil;
	}
	
	public Long getId() {
		return id;
	}

	public String toString() {
		return estadoCivil;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoCivilDTO other = (EstadoCivilDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
