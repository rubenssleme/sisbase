package br.laramara.ti.sislaracommons.dtos.familiar;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ParentescoDTO extends ModeloDTO {

	private static final long serialVersionUID = 5071245499907034494L;

	private Long id;
	private String descricao;

	public ParentescoDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

	public String toString() {
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParentescoDTO other = (ParentescoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
