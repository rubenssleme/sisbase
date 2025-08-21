package br.laramara.sistelemarketingcommons.dtos;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class BairroResultadoDTO extends ResultadoDTO<BairroDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<BairroDTO> getBairrosDto() {
		return objetos;
	}

	public void setBairrosDto(List<BairroDTO> bairrosDto) {
		this.objetos = bairrosDto;
	}
}
