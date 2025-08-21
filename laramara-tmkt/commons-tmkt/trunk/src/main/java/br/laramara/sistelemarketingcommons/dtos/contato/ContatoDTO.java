package br.laramara.sistelemarketingcommons.dtos.contato;

import java.beans.Transient;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class ContatoDTO extends ModeloDTO{

	private static final long serialVersionUID = 2543298485841529989L;

	private Long id;

	private String cpf;

	private String nome;

	private LogradouroDTO cep;

	private String numero;

	private String complemento;

	private String email;
	
	private List<TelefoneDTO> telefonesDto;

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

	public LogradouroDTO getCep() {
		return cep;
	}

	public void setCep(LogradouroDTO cep) {
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

	public List<TelefoneDTO> getTelefonesDto() {
		return telefonesDto;
	}

	public void setTelefonesDto(List<TelefoneDTO> telefonesDto) {
		this.telefonesDto = telefonesDto;
	}
	
	@Transient
	public String getEnderecoCompleto() {
		return cep.getDescricao() + ", " + getNumero() + ", " + getComplemento();
	}
	
	@Transient
	public String getMunicipioEEstado() {
		return cep.obterMunicipioEEstado();
	}
}
