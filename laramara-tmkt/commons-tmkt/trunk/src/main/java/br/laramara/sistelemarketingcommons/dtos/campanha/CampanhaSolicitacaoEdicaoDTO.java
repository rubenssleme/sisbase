package br.laramara.sistelemarketingcommons.dtos.campanha;

public class CampanhaSolicitacaoEdicaoDTO {
	private String token;
	private CampanhaDTO campanhaDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CampanhaDTO getCampanhaDto() {
		return campanhaDto;
	}

	public void setCampanhaDto(CampanhaDTO campanhaDto) {
		this.campanhaDto = campanhaDto;
	}

}
