package br.laramara.ti.sislaracommons.dtos.instituicao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ClassificacaoInstituicaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 1826075373834982310L;
	
	private String classificacaoInstituicao;

	public ClassificacaoInstituicaoDTO(String classificacao){
		this.classificacaoInstituicao = classificacao;
	}
	
	public String toString() {
		return classificacaoInstituicao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassificacaoInstituicaoDTO other = (ClassificacaoInstituicaoDTO) obj;
		if (classificacaoInstituicao == null) {
			if (other.classificacaoInstituicao != null)
				return false;
		} else if (!classificacaoInstituicao
				.equals(other.classificacaoInstituicao))
			return false;
		return true;
	}
}
