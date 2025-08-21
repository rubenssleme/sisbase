package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.MecanismoTransferenciaDTO;


public class ResultadoAutenticacaoDTO extends MecanismoTransferenciaDTO{

	private static final long serialVersionUID = 2624508579094938729L;

	private TokenDTO tokenDto;
	
	public TokenDTO getToken() {
		return tokenDto;
	}

	public void efetuadoComSucesso(TokenDTO tokenDto) {
		this.tokenDto = tokenDto;
		efetuadoComSucesso();
	}
}
