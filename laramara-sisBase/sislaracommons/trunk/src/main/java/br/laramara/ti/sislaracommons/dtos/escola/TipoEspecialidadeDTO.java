package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoEspecialidadeDTO extends ModeloDTO {

	private static final long serialVersionUID = -4073711691676623559L;

	private String tipoEspecialidade;

	public TipoEspecialidadeDTO(String tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}

	public String toString() {
		return tipoEspecialidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoEspecialidadeDTO other = (TipoEspecialidadeDTO) obj;
		if (tipoEspecialidade == null) {
			if (other.tipoEspecialidade != null)
				return false;
		} else if (!tipoEspecialidade.equals(other.tipoEspecialidade))
			return false;
		return true;
	}
}
