package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class DoacaoResultadoDTO extends ResultadoDTO<DoacaoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(DoacaoDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}
	
	public DoacaoDTO obterDoacaoDto() {
		return obterUnico();
	}
	
	public List<DoacaoDTO> getDoacoesDto() {
		return objetos;
	}
	
	public void setDoacoesDto(List<DoacaoDTO> doacoesDto) {
		this.objetos = doacoesDto;
	}

}
