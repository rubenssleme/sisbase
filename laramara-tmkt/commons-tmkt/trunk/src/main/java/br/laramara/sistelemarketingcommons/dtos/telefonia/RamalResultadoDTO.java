package br.laramara.sistelemarketingcommons.dtos.telefonia;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class RamalResultadoDTO extends ResultadoDTO<RamalDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<RamalDTO> getRamaisDto() {
		return objetos;
	}

	public void setRamaisDto(List<RamalDTO> ramaisDto) {
		this.objetos = ramaisDto;
	}
}
