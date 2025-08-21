package br.laramara.sistelemarketingserver.dominio.contato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.sistelemarketingserver.dominio.HistoricoOperacao;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;

@Entity
@Table(name = "historico_status_contato")
public class HistoricoStatusContato extends HistoricoOperacao {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusContato status;

	public HistoricoStatusContato() {
		super();
	}

	public HistoricoStatusContato(StatusContato status, ContaAcesso contaAcesso) {
		super(contaAcesso);
		this.status = status;
	}

	public StatusContato getStatus() {
		return status;
	}

	public String getStringDoStatus() {
		return status.toString();
	}

	@Override
	public String toString() {
		return "HistoricoStatusContato [status=" + status + ", contaAcesso=" + contaAcesso + "]";
	}
}
