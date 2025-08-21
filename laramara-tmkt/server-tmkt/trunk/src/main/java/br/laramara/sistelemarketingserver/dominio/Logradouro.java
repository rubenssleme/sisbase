package br.laramara.sistelemarketingserver.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;

@Entity
@Table(name = "logradouro")
public class Logradouro {

	@Id
	private String cep;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO_LONGA, nullable = false)
	private String descricao;
	
	@Column(name = "bairro", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "Logradouro [cep=" + cep + ", descricao=" + descricao + ", bairro=" + bairro + ", municipio=" + municipio
				+ "]";
	}
}
