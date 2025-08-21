package br.laramara.ti.sislaraserver.dominio.grupo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.doacao.DescricaoRecurso;

@Entity
@Table(name = "recurso")
public class Recurso{
	
	public static final Long ID_BENGALA_BRANCA = new Long(12);
	public static final Long ID_BENGALA_VERDE = new Long(13);
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String descricao;
	
	@Column(name = "cartela_de_selos")
	private boolean cartelaDeSelos;
	
	@Column(name = "disponivel_para_demanda")
	private boolean disponivelParaDemanda;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "recurso_descricao_recurso", joinColumns = { @JoinColumn(name = "id_recurso", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_descricao_recurso", referencedColumnName = "id") })
	private List<DescricaoRecurso> descricoesRecurso;
	
	public Recurso() {

	}

	public Recurso(Long id, String descricao, boolean cartelaDeSelos, boolean disponivelParaDemanda, String valor) {
		this.id = id;
		this.descricao = descricao;	
		this.cartelaDeSelos = cartelaDeSelos;
		this.disponivelParaDemanda = disponivelParaDemanda;
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
		this.descricoesRecurso = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean eCartelaDeSelos() {
		return cartelaDeSelos;
	}
	
	public boolean eBengala() {
		return id.equals(ID_BENGALA_BRANCA) || id.equals(ID_BENGALA_VERDE);
	}
	
	public boolean isDisponivelParaDemanda() {
		return disponivelParaDemanda;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public String obterValor(){
		return DinheiroUtils.obterDinheiro(valor);
	}
	
	public List<DescricaoRecurso> getDescricoesRecurso() {
		Collections.sort(descricoesRecurso, DescricaoRecurso.obterComparador());
		return descricoesRecurso;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Recurso other = (Recurso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Recurso [id=" + id + ", descricao=" + descricao + ", cartelaDeSelos=" + cartelaDeSelos + ", disponivelParaDemanda=" + disponivelParaDemanda + ", valor="
				+ obterValor() + ", descricoesRecursos = " + descricoesRecurso + "]";
	}
}
