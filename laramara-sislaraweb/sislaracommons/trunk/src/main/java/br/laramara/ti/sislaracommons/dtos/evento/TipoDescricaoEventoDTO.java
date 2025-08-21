package br.laramara.ti.sislaracommons.dtos.evento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoDescricaoEventoDTO extends ModeloDTO {
	private static final long serialVersionUID = 400140213289942801L;

	private String tipoDescricaoEvento;

	public TipoDescricaoEventoDTO() {
	}

	public TipoDescricaoEventoDTO(String tipoDescricaoEvento) {
		setTipoDescricaoEvento(tipoDescricaoEvento);
	}

	public String getTipoDescricaoEvento() {
		return tipoDescricaoEvento;
	}

	public void setTipoDescricaoEvento(String tipoDescricaoEvento) {
		this.tipoDescricaoEvento = tipoDescricaoEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoDescricaoEvento == null) ? 0 : tipoDescricaoEvento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoDescricaoEventoDTO other = (TipoDescricaoEventoDTO) obj;
		if (tipoDescricaoEvento == null) {
			if (other.tipoDescricaoEvento != null)
				return false;
		} else if (!tipoDescricaoEvento.equals(other.tipoDescricaoEvento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipoDescricaoEvento;
	}
}
