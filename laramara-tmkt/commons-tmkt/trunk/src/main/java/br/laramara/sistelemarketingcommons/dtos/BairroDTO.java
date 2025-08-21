package br.laramara.sistelemarketingcommons.dtos;

public class BairroDTO extends ModeloDTO implements ItemComboboxComDescricao {

	private static final long serialVersionUID = -5685241751699574712L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return getNome();
	}
}
