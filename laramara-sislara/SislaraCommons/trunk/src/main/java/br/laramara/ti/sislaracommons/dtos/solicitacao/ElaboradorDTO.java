package br.laramara.ti.sislaracommons.dtos.solicitacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ElaboradorDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String elaborador;

	public ElaboradorDTO(String elaborador) {
		this.elaborador = elaborador;
	}

	@Override
	public String toString() {
		return elaborador;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElaboradorDTO other = (ElaboradorDTO) obj;
		if (elaborador == null) {
			if (other.elaborador != null)
				return false;
		} else if (!elaborador.equals(other.elaborador))
			return false;
		return true;
	}
}
