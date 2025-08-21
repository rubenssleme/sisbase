package br.laramara.ti.sislaracommons.dtos.agenda;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class StatusAgendamentoDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;

	private String statusAgendamento;

	public StatusAgendamentoDTO(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public String toString() {
		return statusAgendamento;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusAgendamentoDTO other = (StatusAgendamentoDTO) obj;
		if (statusAgendamento == null) {
			if (other.statusAgendamento != null)
				return false;
		} else if (!statusAgendamento.equals(other.statusAgendamento))
			return false;
		return true;
	}
}
