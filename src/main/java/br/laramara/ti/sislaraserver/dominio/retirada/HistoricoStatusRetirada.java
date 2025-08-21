package br.laramara.ti.sislaraserver.dominio.retirada;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.HistoricoOperacao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "historico_status_retirada")
public class HistoricoStatusRetirada extends HistoricoOperacao {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusRetirada status;

	public HistoricoStatusRetirada() {
	}

	public StatusRetirada getStatus() {
		return status;
	}

	public void setContaAcesso(ContaAcesso contaAcessoOperacao, StatusRetirada statusRetirada) {
		this.contaAcesso = contaAcessoOperacao;
		this.status = statusRetirada;
	}

	public String getStringDoStatus() {
		return status.toString();
	}

	@Override
	public String toString() {
		return "HistoricoStatusRetirada [status=" + status + ", contaAcesso="
				+ contaAcesso + "]";
	}
}
