package br.laramara.sistelemarketingcommons.dtos;

public class MunicipioDTO extends ModeloDTO implements ItemComboboxComIdEDescricao {

	private static final long serialVersionUID = -5685241751699574712L;

	private Long id;
	private String descricao;
	private EstadoDTO estadoDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public EstadoDTO getEstadoDto() {
		return estadoDto;
	}

	public void setEstadoDto(EstadoDTO estadoDto) {
		this.estadoDto = estadoDto;
	}

	public String obterEstadoEMunicipio() {
		return estadoDto.toString() + ", " + getDescricao();
	}
	
	public String obterMunicipioEEstado() {
		return getDescricao() + "/" + getEstadoDto().getDescricao();
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
}
