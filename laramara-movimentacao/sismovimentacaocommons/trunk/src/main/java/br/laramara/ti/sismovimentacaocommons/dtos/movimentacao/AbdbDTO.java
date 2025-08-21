package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class AbdbDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String abdb;

	public AbdbDTO(String abdb) {
		this.abdb = abdb;
	}

	public String toString() {
		return abdb;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbdbDTO other = (AbdbDTO) obj;
		if (abdb == null) {
			if (other.abdb != null)
				return false;
		} else if (!abdb.equals(other.abdb))
			return false;
		return true;
	}

}
