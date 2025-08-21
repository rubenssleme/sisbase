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
@Table(name = "evento_distribuicao_contato")
public class EventoDistribuicaoContato extends HistoricoOperacao {

	@Enumerated(EnumType.STRING)
	@Column(name = "evento", length = TamanhoMaximoGenerico.EVENTO, nullable = false)
	private EventoContato evento;
	
	public EventoDistribuicaoContato() {
		super();
	}

	public EventoDistribuicaoContato(EventoContato evento, ContaAcesso contaAcesso) {
		super(contaAcesso);
		this.evento = evento;
	}

	public boolean eAtuacaoEmContatoIniciado() {
		return evento.equals(EventoContato.ATUACAO_EM_CONTATO_INICIADA);
	}
	
	@Override
	public String getStringDoStatus() {
		return evento.toString();
	}

	@Override
	public String toString() {
		return "EventoDistribuicaoContato [evento=" + evento + "]";
	}
}
