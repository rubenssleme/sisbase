package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class GrupoEtnicoDTO extends ModeloDTO {

	private static final long serialVersionUID = 1L;
	
	private String grupoEtnico;
	
	public GrupoEtnicoDTO(String grupoEtnico){
		this.grupoEtnico = grupoEtnico;
	}
	
	public String toString(){
		return grupoEtnico;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoEtnicoDTO other = (GrupoEtnicoDTO)obj;
		if (grupoEtnico == null){
			if (other.grupoEtnico != null)
				return false;
		}else if (!grupoEtnico.equals(other.grupoEtnico))
			return false;
		return true;
	}
}
