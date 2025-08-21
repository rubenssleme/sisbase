package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class CriterioResultadoDTO extends ResultadoDTO<CriterioDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(CriterioDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}

	public void efetuadoComErro(CriterioDTO objetosDto, String mensagem) {
		efetuadoComErro(Arrays.asList(objetosDto), mensagem);
	}
	
	public CriterioDTO obterCriterioDto() {
		return obterUnico();
	}

	public List<CriterioDTO> getCriteriosDto() {
		return objetos;
	}

	public void setCriteriosDto(List<CriterioDTO> criteriosDto) {
		this.objetos = criteriosDto;
	}
}
