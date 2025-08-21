package br.laramara.sistelemarketingserver.dominio.contato;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingserver.dominio.Historico;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;

@Entity
@Table(name = "distribuicao_contato")
public class DistribuicaoContato extends Validavel implements Identificavel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_campanha")
	private Campanha campanha;
	
	@ManyToOne
	@JoinColumn(name = "id_contato")
	private Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "id_conta_acesso")
	private ContaAcesso operador;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "distribuicao_contato_evento_distribuicao_contato", joinColumns = {
			@JoinColumn(name = "id_distribuicao_contato", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_evento_distribuicao_contato", referencedColumnName = "id") })
	private List<EventoDistribuicaoContato> eventosDistribuicoesContatos;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "distribuicao_contato_evento_telefonia", joinColumns = {
			@JoinColumn(name = "id_distribuicao_contato", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_evento_telefonia", referencedColumnName = "id") })
	private List<EventoTelefonia> eventosTelefonia;

	public DistribuicaoContato() {
		super();
		eventosDistribuicoesContatos = new ArrayList<>();
	}

	public DistribuicaoContato(Campanha campanha, Contato contato, ContaAcesso operador) {
		this();
		this.campanha = campanha;
		this.contato = contato;
		this.operador = operador;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Contato getContato() {
		return contato;
	}

	public boolean possuiOperador(ContaAcesso operador) {
		return this.operador.equals(operador);
	}
	
	public void inicializarAtuacaoNoContato(ContaAcesso contaAcessoResponsavel) {
		eventosDistribuicoesContatos
				.add(new EventoDistribuicaoContato(EventoContato.ATUACAO_EM_CONTATO_INICIADA, contaAcessoResponsavel));
	}
	
	public void finalizarAtuacaoNoContato(ContaAcesso contaAcessoResponsavel) {
		EventoDistribuicaoContato eventoDistribuicaoContatoAtual = obterHistoricoStatusEsperaAtual();
		eventoDistribuicaoContatoAtual.encerrarVigencia();
		eventosDistribuicoesContatos.add(
				new EventoDistribuicaoContato(EventoContato.ATUACAO_EM_CONTATO_FINALIZADA, contaAcessoResponsavel));
	}

	private EventoDistribuicaoContato obterHistoricoStatusEsperaAtual() {
		return Historico.obterHistoricoAtual(eventosDistribuicoesContatos) != null
				? ((EventoDistribuicaoContato) Historico.obterHistoricoAtual(eventosDistribuicoesContatos))
				: null;
	}
	
	public boolean eAtuacaoEmContatoIniciado() {
		return obterHistoricoStatusEsperaAtual().eAtuacaoEmContatoIniciado();
	}
	
	public void adicionarEventoTelefonia(EventoTelefonia eventoTelefonia) {
		eventosTelefonia.add(eventoTelefonia);
	}
	
	public List<EventoTelefonia> getEventosTelefonia() {
		return eventosTelefonia;
	}

	public void setEventosTelefonia(List<EventoTelefonia> eventosTelefonia) {
		this.eventosTelefonia = eventosTelefonia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DistribuicaoContato other = (DistribuicaoContato) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DistribuicaoContato [id=" + id + ", campanha=" + campanha + ", contato=" + contato + ", operador="
				+ operador + ", eventosDistribuicoesContatos=" + eventosDistribuicoesContatos + ", eventosTelefonia=" + eventosTelefonia + "]";
	}
}
