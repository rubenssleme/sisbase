package br.laramara.ti.sislaraserver.dominio.grupo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;



@Entity
@Table(name = "recurso")
public class Recurso{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String descricao;
	
	@Column(name = "cartela_de_selos")
	private boolean cartelaDeSelos;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	public Recurso() {

	}

	public Recurso(Long id, String descricao, boolean cartelaDeSelos, String valor) {
		this.id = id;
		this.descricao = descricao;	
		this.cartelaDeSelos = cartelaDeSelos;
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
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
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public String obterValor(){
		return DinheiroUtils.obterDinheiro(valor);
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
		return "Recurso [id=" + id + ", descricao=" + descricao + ", cartelaDeSelos=" + cartelaDeSelos + ", valor="
				+ obterValor() + "]";
	}
}
