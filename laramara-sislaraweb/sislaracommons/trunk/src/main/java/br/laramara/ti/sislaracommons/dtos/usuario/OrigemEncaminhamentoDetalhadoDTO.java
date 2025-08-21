package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class OrigemEncaminhamentoDetalhadoDTO extends ModeloDTO {

	private static final long serialVersionUID = -6619194507291271553L;

	private Long id;

	private OrigemEncaminhamentoDTO origemEncaminhamentoDto;

	private String profissionalLiberal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrigemEncaminhamentoDTO getOrigemEncaminhamentoDto() {
		return origemEncaminhamentoDto;
	}

	public void setOrigemEncaminhamentoDto(OrigemEncaminhamentoDTO origemEncaminhamentoDto) {
		this.origemEncaminhamentoDto = origemEncaminhamentoDto;
	}

	public String getProfissionalLiberal() {
		return profissionalLiberal;
	}

	public void setProfissionalLiberal(String profissionalLiberal) {
		this.profissionalLiberal = profissionalLiberal;
	}

	@Override
	public String toString() {
		return (origemEncaminhamentoDto != null ? "Origem: " + origemEncaminhamentoDto.toString() + " - " : "")
				+ (profissionalLiberal!= null && !profissionalLiberal.isEmpty() ? "Profissional: " + profissionalLiberal : "");
	}
}
