package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class MetodoResultadoDTO extends ResultadoDTO<MetodoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<MetodoDTO> getMetodosDto() {
		return objetos;
	}

	public void setMetodosDto(List<MetodoDTO> metodosDto) {
		this.objetos = metodosDto;
	}

}
