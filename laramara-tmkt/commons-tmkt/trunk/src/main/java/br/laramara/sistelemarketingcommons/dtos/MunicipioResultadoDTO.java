package br.laramara.sistelemarketingcommons.dtos;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class MunicipioResultadoDTO extends ResultadoDTO<MunicipioDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<MunicipioDTO> getMunicipiosDto() {
		return objetos;
	}

	public void setMunicipiosDto(List<MunicipioDTO> municipiosDto) {
		this.objetos = municipiosDto;
	}

}
