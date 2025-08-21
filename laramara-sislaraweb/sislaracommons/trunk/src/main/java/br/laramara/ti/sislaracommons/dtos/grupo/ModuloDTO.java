package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ModuloDTO extends ModeloDTO {

	private static final long serialVersionUID = 5298687444723240093L;

	private Long id;
	private String nome;

	public ModuloDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloDTO other = (ModuloDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
