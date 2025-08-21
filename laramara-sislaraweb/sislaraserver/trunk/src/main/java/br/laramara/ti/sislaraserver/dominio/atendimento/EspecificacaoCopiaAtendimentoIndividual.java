package br.laramara.ti.sislaraserver.dominio.atendimento;

import br.laramara.ti.sislaraserver.dominio.Horario;

public class EspecificacaoCopiaAtendimentoIndividual {

	private Horario horario;

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "EspecificacaoCopiaAtendimentoIndividual [horario=" + horario
				+ "]";
	}
}
