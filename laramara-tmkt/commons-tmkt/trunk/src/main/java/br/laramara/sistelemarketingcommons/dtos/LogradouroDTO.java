package br.laramara.sistelemarketingcommons.dtos;

public class LogradouroDTO extends ModeloDTO {

	private static final long serialVersionUID = -5685241751699574712L;

	private String cep;
	private String descricao;
	private String bairro;
	private MunicipioDTO municipioDto;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public MunicipioDTO getMunicipioDto() {
		return municipioDto;
	}

	public void setMunicipioDto(MunicipioDTO municipioDto) {
		this.municipioDto = municipioDto;
	}
	
	public String obterMunicipioEEstado() {
		return municipioDto.obterMunicipioEEstado();
	}
}
