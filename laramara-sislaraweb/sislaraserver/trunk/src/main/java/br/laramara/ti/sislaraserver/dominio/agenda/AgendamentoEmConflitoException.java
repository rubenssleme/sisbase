package br.laramara.ti.sislaraserver.dominio.agenda;

public class AgendamentoEmConflitoException extends Exception {
	private static final long serialVersionUID = 4657708219957218937L;

	public AgendamentoEmConflitoException(String agendamentosEmConflito) {
		super("O profissinal já possui agendamentos. "
				+ agendamentosEmConflito);
	}
}
