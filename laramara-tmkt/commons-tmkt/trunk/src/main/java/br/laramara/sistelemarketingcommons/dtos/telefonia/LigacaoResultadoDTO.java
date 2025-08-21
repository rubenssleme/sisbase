package br.laramara.sistelemarketingcommons.dtos.telefonia;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class LigacaoResultadoDTO extends ResultadoDTO<LigacaoDTO> {

	private static final long serialVersionUID = 5181220264091244916L;

	public void efetuadoComErro(LigacaoDTO objetosDto, String mensagem) {
		efetuadoComErro(Arrays.asList(objetosDto), mensagem);
	}
	
	public LigacaoDTO obterLigacaoDto() {
		return obterUnico();
	}

	public List<LigacaoDTO> getLigacoesDto() {
		return objetos;
	}

	public void setLigacoesDto(List<LigacaoDTO> criteriosDto) {
		this.objetos = criteriosDto;
	}
}
