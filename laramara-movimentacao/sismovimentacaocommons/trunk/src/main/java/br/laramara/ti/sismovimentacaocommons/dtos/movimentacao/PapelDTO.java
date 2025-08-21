package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class PapelDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String papel;

	public PapelDTO(String papel) {
		this.papel = papel;
	}

	public String toString() {
		return papel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PapelDTO other = (PapelDTO) obj;
		if (papel == null) {
			if (other.papel != null)
				return false;
		} else if (!papel.equals(other.papel))
			return false;
		return true;
	}

}
