package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class FrequenciaContribuicaoDTO extends ModeloDTO {

	private static final long serialVersionUID = -5665635472935397103L;

	private String frequencia;

	public FrequenciaContribuicaoDTO(String frequencia) {
		this.frequencia = frequencia;
	}

	public String toString() {
		return frequencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrequenciaContribuicaoDTO other = (FrequenciaContribuicaoDTO) obj;
		if (frequencia == null) {
			if (other.frequencia != null)
				return false;
		} else if (!frequencia.equals(other.frequencia))
			return false;
		return true;
	}
}
