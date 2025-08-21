package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoLeituraDTO extends ModeloDTO {

	private static final long serialVersionUID = -5665635472935397103L;

	private String tipoLeitura;

	public TipoLeituraDTO(String tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}

	public String toString() {
		return tipoLeitura;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoLeituraDTO other = (TipoLeituraDTO) obj;
		if (tipoLeitura == null) {
			if (other.tipoLeitura != null)
				return false;
		} else if (!tipoLeitura.equals(other.tipoLeitura))
			return false;
		return true;
	}
}
