package br.laramara.ti.sismovimentacaoserver.dominio.movimentacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sismovimentacaoserver.dominio.HistoricoOperacao;
import br.laramara.ti.sismovimentacaoserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;


@Entity
@Table(name = "historico_status")
public class HistoricoStatus extends HistoricoOperacao{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private Status status;

	public HistoricoStatus(){
	}
	
	public HistoricoStatus(Status status, ContaAcesso contaAcesso) {
		super(contaAcesso);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
	
	public boolean possuiIdentificacao() {
		return id != null;
	}

	@Override
	public String getStringDoStatus() {
		return status.toString();
	}
}
