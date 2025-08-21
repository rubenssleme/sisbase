package br.laramara.ti.sislaraserver.dominio.doacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.HistoricoOperacao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "historico_status_demanda")
public class HistoricoStatusDemanda extends HistoricoOperacao {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusDemanda status;

	public HistoricoStatusDemanda() {
	}

	public HistoricoStatusDemanda(StatusDemanda status) {
		this.status = status;
	}

	public StatusDemanda getStatus() {
		return status;
	}

	public boolean possuiStatus() {
		return status != null;
	}

	public void setContaAcesso(ContaAcesso contaAcessoOperacao) {
		this.contaAcesso = contaAcessoOperacao;
	}

	public String getStringDoStatus() {
		return status.toString();
	}

	@Override
	public String toString() {
		return "HistoricoStatusDemanda [status=" + status + ", contaAcesso="
				+ contaAcesso + "]";
	}
}
