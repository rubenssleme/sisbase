package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "historico_status")
public class HistoricoStatus extends Historico {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private Status status;

	public HistoricoStatus() {
	}

	public HistoricoStatus(Status status) {
		super();
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
	
	public boolean possuiStatus(){
		return status != null;
	}
}
