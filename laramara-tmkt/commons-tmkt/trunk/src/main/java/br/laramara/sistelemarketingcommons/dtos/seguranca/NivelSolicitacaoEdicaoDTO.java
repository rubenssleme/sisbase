package br.laramara.sistelemarketingcommons.dtos.seguranca;

public class NivelSolicitacaoEdicaoDTO {
	private String token;
	private NivelDTO nivelDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public NivelDTO getNivelDto() {
		return nivelDto;
	}

	public void setNivelDto(NivelDTO nivelDto) {
		this.nivelDto = nivelDto;
	}
}
