package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EnderecoDTO extends ModeloDTO {

	private static final long serialVersionUID = -5486724925186172180L;

	private Long id;
	private String cep;
	private String endereco;
	private String numero;
	private String complemento;
	private ZonaDTO zonaDto;
	private String bairro;
	private MunicipioDTO municipioDto;
	private UfDTO ufDto;
	private PaisDTO paisDto;
	private TipoEnderecoDTO tipoEnderecoDto;

	public EnderecoDTO() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public ZonaDTO getZonaDto() {
		return zonaDto;
	}

	public void setZonaDto(ZonaDTO zonaDto) {
		this.zonaDto = zonaDto;
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

	public void setMunicipioDto(MunicipioDTO municipio) {
		this.municipioDto = municipio;
	}

	public UfDTO getUfDto() {
		return ufDto;
	}

	public void setUfDto(UfDTO uf) {
		this.ufDto = uf;
	}

	public PaisDTO getPaisDto() {
		return paisDto;
	}

	public void setPaisDto(PaisDTO paisDto) {
		this.paisDto = paisDto;
	}

	public TipoEnderecoDTO getTipoEnderecoDto() {
		return tipoEnderecoDto;
	}

	public void setTipoEnderecoDto(TipoEnderecoDTO tipoEnderecoDto) {
		this.tipoEnderecoDto = tipoEnderecoDto;
	}

}
