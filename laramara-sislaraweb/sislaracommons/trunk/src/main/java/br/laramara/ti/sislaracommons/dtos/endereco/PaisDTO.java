package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PaisDTO extends ModeloDTO {
 
	private static final long serialVersionUID = 5699596010585052345L;
	
	private Long id;
	private String nome;
	
	public PaisDTO() {
	}

	public PaisDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

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
		PaisDTO other = (PaisDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
