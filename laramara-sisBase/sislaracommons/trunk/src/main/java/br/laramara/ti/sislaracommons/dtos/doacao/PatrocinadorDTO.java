package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PatrocinadorDTO extends ModeloDTO {

	private static final long serialVersionUID = 6526881775584192408L;

	private Long id;
	private String cpfCnpj;
	private String nome;
	
	public PatrocinadorDTO(String cpfCnpj, String nome) {
		super();
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
	}
	
	public PatrocinadorDTO(Long id, String cpfCnpj, String nome) {
		this(cpfCnpj, nome);
		this.id = id;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
}
