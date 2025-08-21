package br.laramara.ti.sislaraserver.dominio.solicitacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.HistoricoOperacao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "historico_status_solicitacao_relatorio")
public class HistoricoStatusSolicitacaoRelatorio extends HistoricoOperacao {

	@Enumerated(EnumType.STRING)
	@Column(name = "status_solicitacao_relatorio", length = TamanhoMaximoGenerico.STATUS)
	private StatusSolicitacaoRelatorio statusSolicitacaoRelatorio;

	public HistoricoStatusSolicitacaoRelatorio() {
	}

	public void setContaAcesso(ContaAcesso contaAcessoOperacao, StatusSolicitacaoRelatorio statusSolicitacaoRelatorio) {
		this.contaAcesso = contaAcessoOperacao;
		this.statusSolicitacaoRelatorio = statusSolicitacaoRelatorio;
	}

	public StatusSolicitacaoRelatorio getStatusSolicitacaoRelatorio() {
		return statusSolicitacaoRelatorio;
	}

	@Override
	public String getStringDoStatus() {
		return statusSolicitacaoRelatorio.toString();
	}

	public boolean possuiStatus() {
		return statusSolicitacaoRelatorio != null;
	}
}
