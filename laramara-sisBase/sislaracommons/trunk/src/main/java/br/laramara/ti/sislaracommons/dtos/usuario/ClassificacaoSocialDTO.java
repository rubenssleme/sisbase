package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ClassificacaoSocialDTO extends ModeloDTO {

	private static final long serialVersionUID = 15177248774012916L;

	private String classificacaoSocial;

	public ClassificacaoSocialDTO(String classificacaoSocial) {
		this.classificacaoSocial = classificacaoSocial;
	}

	public String toString() {
		return classificacaoSocial;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassificacaoSocialDTO other = (ClassificacaoSocialDTO) obj;
		if (classificacaoSocial == null) {
			if (other.classificacaoSocial != null)
				return false;
		} else if (!classificacaoSocial.equals(other.classificacaoSocial))
			return false;
		return true;
	}
}
