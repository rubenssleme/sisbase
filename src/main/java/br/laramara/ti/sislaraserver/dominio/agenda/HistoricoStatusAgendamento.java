package br.laramara.ti.sislaraserver.dominio.agenda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.HistoricoOperacao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "historico_status_agendamento")
public class HistoricoStatusAgendamento extends HistoricoOperacao{

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusAgendamento status;

	public HistoricoStatusAgendamento() {
	}

	public HistoricoStatusAgendamento(StatusAgendamento status, ContaAcesso contaAcesso) {
		super(contaAcesso);
		this.status = status;
	}

	public StatusAgendamento getStatus() {
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
		return "HistoricoStatusAgendamento [status=" + status
				+ ", contaAcesso=" + contaAcesso + "]";
	}
}
