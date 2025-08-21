package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class SituacaoEducacionalDTO extends ModeloDTO {

	private static final long serialVersionUID = -4038837616322642991L;
	
	private String situacao;

	public SituacaoEducacionalDTO(String situacao) {
		this.situacao = situacao;
	}

	public String toString() {
		return situacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SituacaoEducacionalDTO other = (SituacaoEducacionalDTO) obj;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}
}
