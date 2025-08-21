package br.laramara.sistelemarketingcommons.dtos;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class EstadoResultadoDTO extends ResultadoDTO<EstadoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<EstadoDTO> getEstadosDto() {
		return objetos;
	}

	public void setEstadosDto(List<EstadoDTO> estadosDto) {
		this.objetos = estadosDto;
	}
}
