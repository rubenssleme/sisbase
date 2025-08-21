package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoAcaoCondutaDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String tipoAcaoConduta;

	public TipoAcaoCondutaDTO(String tipoAcaoConduta) {
		this.tipoAcaoConduta = tipoAcaoConduta;
	}

	@Override
	public String toString() {
		return tipoAcaoConduta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoAcaoCondutaDTO other = (TipoAcaoCondutaDTO) obj;
		if (tipoAcaoConduta == null) {
			if (other.tipoAcaoConduta != null)
				return false;
		} else if (!tipoAcaoConduta.equals(other.tipoAcaoConduta))
			return false;
		return true;
	}
}
