package br.laramara.ti.sislaraserver.dominio;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@MappedSuperclass
public abstract class HistoricoOperacao extends Historico{

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_conta_acesso")
	protected ContaAcesso contaAcesso;

	public HistoricoOperacao() {
	}

	public HistoricoOperacao(ContaAcesso contaAcesso) {
		super();
		this.contaAcesso = contaAcesso;
	}
	
	public abstract String getStringDoStatus();

	public ContaAcesso getContaAcesso() {
		return contaAcesso;
	}
	
	public boolean possuiContaAcesso() {
		return contaAcesso != null;
	}
	
}
