package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PeriodoDoencaDTO extends ModeloDTO {
	
	private static final long serialVersionUID = -9143396164202455424L;
	
	private Long id;
	private DoencaDTO doencaDto;
	private String dataInicial;
	private String dataFinal;
	private String obs;
	
	public PeriodoDoencaDTO() {
	}
	
	public Long getId() {
		return id;
	}

	public DoencaDTO getDoencaDto() {
		return doencaDto;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDoencaDto(DoencaDTO doencaDto) {
		this.doencaDto = doencaDto;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoDoencaDTO other = (PeriodoDoencaDTO) obj;
		if (doencaDto == null) {
			if (other.doencaDto != null)
				return false;
		} else if (!doencaDto.equals(other.doencaDto))
			return false;
		return true;
	}

	public String toString() {
		return doencaDto.toString() + (!obs.isEmpty() ? " - " + obs : "")
				+ " - " + dataInicial + " - " + dataFinal;
	}
}
