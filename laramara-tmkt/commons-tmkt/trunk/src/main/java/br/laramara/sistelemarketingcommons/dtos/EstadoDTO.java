package br.laramara.sistelemarketingcommons.dtos;

public class EstadoDTO extends ModeloDTO implements ItemComboboxComIdEDescricao {

	private static final long serialVersionUID = -5685241751699574712L;

	private Long id;
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}
}
