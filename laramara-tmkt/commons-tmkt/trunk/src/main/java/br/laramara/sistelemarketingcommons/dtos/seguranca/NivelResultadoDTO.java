package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

public class NivelResultadoDTO extends ResultadoDTO<NivelDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(NivelDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}
	
	public NivelDTO obterNivelDto() {
		return obterUnico();
	}

	public List<NivelDTO> getNiveisDto() {
		return objetos;
	}

	public void setNiveisDto(List<NivelDTO> niveisDto) {
		this.objetos = niveisDto;
	}
}
