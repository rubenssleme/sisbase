package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PeriodoDTO extends ModeloDTO{
	
	private static final long serialVersionUID = 287906741158119245L;
	
	private String periodo;

	public PeriodoDTO(String periodo) {
		this.periodo = periodo;
	}

	public String toString() {
		return periodo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoDTO other = (PeriodoDTO) obj;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		return true;
	}


}
