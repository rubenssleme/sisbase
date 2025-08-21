package br.laramara.ti.sislaracommons.dtos.solicitacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusSolicitacaoRelatorioDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String status;

	public StatusSolicitacaoRelatorioDTO(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusSolicitacaoRelatorioDTO other = (StatusSolicitacaoRelatorioDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
