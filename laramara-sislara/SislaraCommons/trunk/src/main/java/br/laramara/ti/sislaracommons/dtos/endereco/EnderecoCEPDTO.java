package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EnderecoCEPDTO extends ModeloDTO {

	private static final long serialVersionUID = -7179294584129241253L;
	
	private PaisDTO paisDto;
	private UfDTO ufDto;
	private MunicipioDTO municipioDto;
	private String bairro;
	private String endereco;

	public PaisDTO getPaisDto() {
		return paisDto;
	}

	public void setPaisDto(PaisDTO paisDto) {
		this.paisDto = paisDto;
	}

	public UfDTO getUfDto() {
		return ufDto;
	}

	public void setUfDto(UfDTO ufDto) {
		this.ufDto = ufDto;
	}

	public MunicipioDTO getMunicipioDto() {
		return municipioDto;
	}

	public void setMunicipioDto(MunicipioDTO municipioDto) {
		this.municipioDto = municipioDto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
