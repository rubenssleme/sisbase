package br.laramara.ti.sislaracommons.dtos;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;

public class InformacaoEssencialDTO extends ModeloDTO implements Identificavel {
	
	private static final long serialVersionUID = -6071952310627582696L;
	
	private Long id;
	private String nome;
	private String dataNascimento;
	private String idade;
	private String rg;
	private String cpf;
	private ContatoDTO contatoDto;
	private EnderecoDTO enderecoDto;
	private boolean usuarioAssociado;

	public InformacaoEssencialDTO(){
		contatoDto = new ContatoDTO();
		enderecoDto = new EnderecoDTO();
	}

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
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public ContatoDTO getContatoDto() {
		return contatoDto;
	}

	public void setContatoDto(ContatoDTO contatoDto) {
		this.contatoDto = contatoDto;
	}

	public void setUsuarioAssociado(boolean usuarioAssociado) {
		this.usuarioAssociado = usuarioAssociado;
	}

	public boolean possuiUsuarioAssociado(){
		return this.usuarioAssociado;
	}
	
	public EnderecoDTO getEnderecoDto() {
		return enderecoDto;
	}
	
	public void setEnderecoDto(EnderecoDTO enderecoDto) {
		this.enderecoDto = enderecoDto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
