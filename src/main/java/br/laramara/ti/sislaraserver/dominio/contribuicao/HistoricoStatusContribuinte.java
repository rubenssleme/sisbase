package br.laramara.ti.sislaraserver.dominio.contribuicao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "historico_status_contribuinte")
public class HistoricoStatusContribuinte extends Historico {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusContribuinte status;

	public HistoricoStatusContribuinte() {
	}

	public HistoricoStatusContribuinte(StatusContribuinte status) {
		this.status = status;
	}

	public StatusContribuinte getStatus() {
		return status;
	}

	public boolean possuiStatus() {
		return status != null;
	}

	public String getStringDoStatus() {
		return status.toString();
	}

	@Override
	public String toString() {
		return "HistoricoStatusContribuinte [status=" + status + "]";
	}
}
