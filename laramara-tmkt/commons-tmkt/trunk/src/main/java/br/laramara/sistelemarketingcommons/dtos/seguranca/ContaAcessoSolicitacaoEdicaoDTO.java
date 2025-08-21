package br.laramara.sistelemarketingcommons.dtos.seguranca;

public class ContaAcessoSolicitacaoEdicaoDTO {

	private String token;
	private ContaAcessoDTO contaAcessoDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ContaAcessoDTO getContaAcessoDto() {
		return contaAcessoDto;
	}

	public void setContaAcessoDto(ContaAcessoDTO contaAcessoDto) {
		this.contaAcessoDto = contaAcessoDto;
	}
}
