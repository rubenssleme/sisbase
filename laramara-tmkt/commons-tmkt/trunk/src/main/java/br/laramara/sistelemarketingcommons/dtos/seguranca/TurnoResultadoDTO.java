package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.List;

public class TurnoResultadoDTO extends ResultadoDTO<TurnoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<TurnoDTO> getTurnosDto() {
		return objetos;
	}

	public void setTurnosDto(List<TurnoDTO> turnosDto) {
		this.objetos = turnosDto;
	}

}
