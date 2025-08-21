package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PrestacaoContaDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String prestacaoConta;

	public PrestacaoContaDTO(String prestacaoConta) {
		this.prestacaoConta = prestacaoConta;
	}

	public String toString() {
		return prestacaoConta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestacaoContaDTO other = (PrestacaoContaDTO) obj;
		if (prestacaoConta == null) {
			if (other.prestacaoConta != null)
				return false;
		} else if (!prestacaoConta.equals(other.prestacaoConta))
			return false;
		return true;
	}

}
