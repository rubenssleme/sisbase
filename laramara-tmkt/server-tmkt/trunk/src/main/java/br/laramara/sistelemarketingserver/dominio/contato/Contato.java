package br.laramara.sistelemarketingserver.dominio.contato;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import br.laramara.sistelemarketingserver.dominio.Logradouro;
import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;

@Entity
@Table(name = "contato")
public class Contato implements Serializable{
	
	private static final long serialVersionUID = -99748161922449073L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cpf", length = TamanhoMaximoGenerico.CPF)
	private String cpf;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "cep")
	private Logradouro cep;
	
	@Column(name = "numero", length = TamanhoMaximoGenerico.NUMERO)
	private String numero;
	
	@Column(name = "complemento", length = TamanhoMaximoGenerico.COMPLEMENTO)
	private String complemento;
	
	@Column(name = "email", length = TamanhoMaximoGenerico.EMAIL)
	private String email;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "contato_historico_status_contato", joinColumns = { @JoinColumn(name = "id_contato", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_historico_status_contato", referencedColumnName = "id") })
	private List<HistoricoStatusContato> historicoStatus;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "contato_telefone", joinColumns = { @JoinColumn(name = "id_contato", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_telefone", referencedColumnName = "id") })
	private List<Telefone> telefones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Logradouro getCep() {
		return cep;
	}

	public void setCep(Logradouro cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Contato other = (Contato) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", cep=" + cep + ", numero=" + numero
				+ ", complemento=" + complemento + ", email=" + email + ", historicoStatus=" + historicoStatus
				+ ", telefones=" + telefones + "]";
	}
}
