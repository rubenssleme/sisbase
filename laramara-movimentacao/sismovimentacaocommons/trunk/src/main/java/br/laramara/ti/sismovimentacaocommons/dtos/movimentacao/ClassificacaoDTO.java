package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class ClassificacaoDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String classificacao;

	public ClassificacaoDTO(String classificacao) {
		this.classificacao = classificacao;
	}

	public String toString() {
		return classificacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassificacaoDTO other = (ClassificacaoDTO) obj;
		if (classificacao == null) {
			if (other.classificacao != null)
				return false;
		} else if (!classificacao.equals(other.classificacao))
			return false;
		return true;
	}
}
