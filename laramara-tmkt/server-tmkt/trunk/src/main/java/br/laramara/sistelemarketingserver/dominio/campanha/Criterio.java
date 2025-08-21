package br.laramara.sistelemarketingserver.dominio.campanha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingserver.dominio.Municipio;
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;

@Entity
@Table(name = "criterio")
public class Criterio extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;
	
	@Column(name = "bairro", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String bairro;
	
	@Column(name = "quantidade_distribuir")
	private Integer quantidadeDistribuir;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Integer getQuantidadeDistribuir() {
		return quantidadeDistribuir;
	}

	public void setQuantidadeDistribuir(Integer quantidadeDistribuir) {
		this.quantidadeDistribuir = quantidadeDistribuir;
	}
	
	public boolean eCriterioDeBairro() {
		return municipio != null && bairro != null;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (estaComCampoNuloOuVazio(nome)) {
			adicionarErro("Insira um nome válido.");
		}
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um nome contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (municipio == null) {
			adicionarErro("Insira um município.");
		}
		if (estaComCampoNuloOuVazio(bairro)) {
			adicionarErro("Insira um bairro.");
		}
		if (quantidadeDistribuir == null) {
			adicionarErro("Insira uma quantidade a distribuir.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criterio other = (Criterio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Criterio [id=" + id + ", nome=" + nome + ", municipio=" + municipio + ", bairro=" + bairro
				+ ", quantidadeDistribuir=" + quantidadeDistribuir + "]";
	}
}
