package br.laramara.ti.sislaraserver.dominio.grupo;

import br.laramara.ti.sislaraserver.dominio.Horario;

public class EspecificacaoGeracaoAtendimento {
	private Long idModuloPeriodo;
	private String data;
	private Horario horario;

	public EspecificacaoGeracaoAtendimento(Long idModuloPeriodo, String data, Horario horario) {
		super();
		this.idModuloPeriodo = idModuloPeriodo;
		this.data = data;
		this.horario = horario;
	}

	public Long getIdModuloPeriodo() {
		return idModuloPeriodo;
	}

	public void setIdModuloPeriodo(Long idModuloPeriodo) {
		this.idModuloPeriodo = idModuloPeriodo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Horario getHorario() {
		return horario != null ? horario : new Horario();
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
}
