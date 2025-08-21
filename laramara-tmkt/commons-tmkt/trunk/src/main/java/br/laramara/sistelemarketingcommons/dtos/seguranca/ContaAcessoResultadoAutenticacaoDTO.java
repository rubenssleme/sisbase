package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

public class ContaAcessoResultadoAutenticacaoDTO extends ResultadoDTO<ContaAcessoDTO> {

	private static final long serialVersionUID = 7919589179106371404L;

	private String token;

	public void efetuadoComSucesso(ContaAcessoDTO objetosDto, String token) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
		setToken(token);
	}

	public ContaAcessoDTO obterContaAcessoDto() {
		return obterUnico();
	}

	public List<ContaAcessoDTO> getContasAcessosDto() {
		return objetos;
	}

	public void setContasAcessosDto(List<ContaAcessoDTO> contasAcessosDto) {
		this.objetos = contasAcessosDto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
