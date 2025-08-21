package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

public class ContaAcessoResultadoDTO extends ResultadoDTO<ContaAcessoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(ContaAcessoDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
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
}
