package br.laramara.sistelemarketingcommons.dtos.contato;

import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class TelefoneDTO extends ModeloDTO{

	private static final long serialVersionUID = 2543298485841529989L;

	private Long id;

	private String ddd;

	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "(" + ddd + ")" + telefone;
	}
}
