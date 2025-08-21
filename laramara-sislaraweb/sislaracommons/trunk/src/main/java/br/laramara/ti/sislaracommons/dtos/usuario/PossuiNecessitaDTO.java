package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PossuiNecessitaDTO extends ModeloDTO {

	private static final long serialVersionUID = -7574775370874366470L;

	private String relacaoRecurso;

	public PossuiNecessitaDTO(String relacaoRecurso) {
		this.relacaoRecurso = relacaoRecurso;
	}

	public String toString() {
		return relacaoRecurso;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PossuiNecessitaDTO other = (PossuiNecessitaDTO) obj;
		if (relacaoRecurso == null) {
			if (other.relacaoRecurso != null)
				return false;
		} else if (!relacaoRecurso.equals(other.relacaoRecurso))
			return false;
		return true;
	}

}
