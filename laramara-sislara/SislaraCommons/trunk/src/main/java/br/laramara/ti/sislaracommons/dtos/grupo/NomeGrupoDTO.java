package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class NomeGrupoDTO extends ModeloDTO {

	private static final long serialVersionUID = 2986920394247339303L;

	private Long id;
	private String nomeGrupo;

	public NomeGrupoDTO(Long id, String nomeGrupo) {
		this.id = id;
		this.nomeGrupo = nomeGrupo;
	}

	public Long getId(){
		return id;
	}
	
	public String toString() {
		return nomeGrupo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NomeGrupoDTO other = (NomeGrupoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
