package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class FibraDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String fibra;

	public FibraDTO(String fibra) {
		this.fibra = fibra;
	}

	public String toString() {
		return fibra;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FibraDTO other = (FibraDTO) obj;
		if (fibra == null) {
			if (other.fibra != null)
				return false;
		} else if (!fibra.equals(other.fibra))
			return false;
		return true;
	}

}
