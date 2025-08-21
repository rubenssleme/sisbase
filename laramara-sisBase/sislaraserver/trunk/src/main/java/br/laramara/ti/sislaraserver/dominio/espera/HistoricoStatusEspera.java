package br.laramara.ti.sislaraserver.dominio.espera;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.HistoricoOperacao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "historico_status_espera")
public class HistoricoStatusEspera extends HistoricoOperacao{

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusEspera status;
	
	public HistoricoStatusEspera(){
		super();
	}
	
	public HistoricoStatusEspera(StatusEspera status, ContaAcesso contaAcesso) {
		super(contaAcesso);
		this.status = status;
	}

	public StatusEspera getStatus() {
		return status;
	}
	
	public String getStringDoStatus() {
		return status.toString();
	}

	@Override
	public String toString() {
		return "HistoricoStatusEspera [status=" + status + ", contaAcesso="
				+ contaAcesso + "]";
	}
}
