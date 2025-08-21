package br.laramara.ti.sislaraserver.dominio.doacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.endereco.TamanhoMaximoEndereco;

@Entity
@Table(name = "filial")
public class Filial {
	
	public static final Long ID_FILIAL_1 = new Long(1);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "cnpj", length = TamanhoMaximoGenerico.CNPJ)
	private String cnpj;
	
	@Column(name = "endereco", length = TamanhoMaximoEndereco.ENDERECO)
	private String endereco;
	
	@Column(name = "cep", length = TamanhoMaximoEndereco.CEP)
	private String cep;

	public Filial(){
	}
	
	public Filial(Long id){
		this.id = id;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public boolean eFilial1(){
		return id.equals(ID_FILIAL_1);
	}

	@Override
	public String toString() {
		return "Filial [id=" + id + ", cnpj=" + cnpj + ", endereco=" + endereco + ", cep=" + cep + "]";
	}
}
