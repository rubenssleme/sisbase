package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class CampanhaResultadoDTO extends ResultadoDTO<CampanhaDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(CampanhaDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}
	
	public CampanhaDTO obterCampanhaDto() {
		return obterUnico();
	}
	
	public List<CampanhaDTO> getCampanhasDto() {
		return objetos;
	}
	
	public void setCampanhasDto(List<CampanhaDTO> campanhasDto) {
		this.objetos = campanhasDto;
	}
}
