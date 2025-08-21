package br.laramara.ti.sislaracommons.dtos.instituicao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoInstituicaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 8256157620805309772L;

	private String tipo;
	
	public TipoInstituicaoDTO(String tipo){
		this.tipo = tipo;
	}

	public String toString() {
		return tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoInstituicaoDTO other = (TipoInstituicaoDTO) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}
