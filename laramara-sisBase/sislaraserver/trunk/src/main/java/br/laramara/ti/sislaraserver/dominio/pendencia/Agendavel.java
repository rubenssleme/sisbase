package br.laramara.ti.sislaraserver.dominio.pendencia;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;

public interface Agendavel {
	public boolean estaAgendado();
	public Agendamento getAgendamento();
}
