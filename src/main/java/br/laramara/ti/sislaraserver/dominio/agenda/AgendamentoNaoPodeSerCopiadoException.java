package br.laramara.ti.sislaraserver.dominio.agenda;

public class AgendamentoNaoPodeSerCopiadoException extends Exception {

	private static final long serialVersionUID = 1537019632916597266L;

	public AgendamentoNaoPodeSerCopiadoException() {
		super("O Agendamento n�o pode ser copiado.");
	}
}
