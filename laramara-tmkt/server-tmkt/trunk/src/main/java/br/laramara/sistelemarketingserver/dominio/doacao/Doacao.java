package br.laramara.sistelemarketingserver.dominio.doacao;

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
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;

@Entity
@Table(name = "doacao")
public class Doacao extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "doacao_valor_detalhado", joinColumns = {
			@JoinColumn(name = "id_doacao", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_valor_detalhado", referencedColumnName = "id") })
	private List<ValorDetalhado> valoresDetalhados;
	
	@ManyToOne
	@JoinColumn(name = "id_distribuicao_contato")
	private DistribuicaoContato distribuicaoContato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ValorDetalhado> getValoresDetalhados() {
		return valoresDetalhados;
	}

	public void setValoresDetalhados(List<ValorDetalhado> valoresDetalhados) {
		this.valoresDetalhados = valoresDetalhados;
	}
	
	public DistribuicaoContato getDistribuicaoContato() {
		return distribuicaoContato;
	}

	public void setDistribuicaoContato(DistribuicaoContato distribuicaoContato) {
		this.distribuicaoContato = distribuicaoContato;
	}
	
	public boolean eNova() {
		return id == null;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Doacao [id=" + id + ", valoresDetalhados=" + valoresDetalhados + ", distribuicaoContato=" + distribuicaoContato + "]";
	}
}
