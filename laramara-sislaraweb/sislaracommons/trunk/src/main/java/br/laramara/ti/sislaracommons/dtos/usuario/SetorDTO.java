package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class SetorDTO extends ModeloDTO {

	private static final long serialVersionUID = -7574775370874366470L;

	private String setor;

	public SetorDTO(String setor) {
		this.setor = setor;
	}

	public String toString() {
		return setor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetorDTO other = (SetorDTO) obj;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		return true;
	}
}
