package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoCertidaoDTO extends ModeloDTO {

	private static final long serialVersionUID = -5665635472935397103L;

	private String tipoCertidao;

	public TipoCertidaoDTO(String tipoCertidao) {
		this.tipoCertidao = tipoCertidao;
	}

	public String toString() {
		return tipoCertidao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCertidaoDTO other = (TipoCertidaoDTO) obj;
		if (tipoCertidao == null) {
			if (other.tipoCertidao != null)
				return false;
		} else if (!tipoCertidao.equals(other.tipoCertidao))
			return false;
		return true;
	}
}
