package br.laramara.ti.sislaraserver.dominio.agenda;

public class AgendamentoComDataAnteriorADataAtual extends Exception {
	private static final long serialVersionUID = 4657708219957218937L;

	public AgendamentoComDataAnteriorADataAtual() {
		super("Agendamento com data anterior a data atual. ");
	}
}
