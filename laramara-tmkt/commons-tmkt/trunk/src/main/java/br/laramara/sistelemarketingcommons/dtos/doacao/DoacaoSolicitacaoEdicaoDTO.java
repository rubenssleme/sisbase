package br.laramara.sistelemarketingcommons.dtos.doacao;

public class DoacaoSolicitacaoEdicaoDTO {
	private String token;
	private DoacaoDTO doacaoDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DoacaoDTO getDoacaoDto() {
		return doacaoDto;
	}

	public void setDoacaoDto(DoacaoDTO doacaoDto) {
		this.doacaoDto = doacaoDto;
	}
}
