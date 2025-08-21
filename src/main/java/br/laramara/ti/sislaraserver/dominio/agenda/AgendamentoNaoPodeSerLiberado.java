package br.laramara.ti.sislaraserver.dominio.agenda;

public class AgendamentoNaoPodeSerLiberado extends Exception {

	private static final long serialVersionUID = 1537019632916597266L;

	public AgendamentoNaoPodeSerLiberado(String explicacao) {
		super("O Agendamento não pode ser liberado" + explicacao);
	}
}
