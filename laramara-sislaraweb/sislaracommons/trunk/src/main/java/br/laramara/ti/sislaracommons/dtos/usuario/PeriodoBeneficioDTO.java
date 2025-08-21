package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PeriodoBeneficioDTO extends ModeloDTO {

	private static final long serialVersionUID = 5927364961824787757L;

	private Long id;
	private BeneficioDTO beneficioDto;
	private String dataInicial;
	private String dataFinal;
	private StatusBeneficioDTO statusDto;
	
	public PeriodoBeneficioDTO() {
	}

	public Long getId() {
		return id;
	}

	public BeneficioDTO getBeneficioDto() {
		return beneficioDto;
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

	public void setBeneficioDto(BeneficioDTO beneficioDto) {
		this.beneficioDto = beneficioDto;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public StatusBeneficioDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusBeneficioDTO statusDto) {
		this.statusDto = statusDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoBeneficioDTO other = (PeriodoBeneficioDTO) obj;
		if (beneficioDto == null) {
			if (other.beneficioDto != null)
				return false;
		} else if (!beneficioDto.equals(other.beneficioDto))
			return false;
		return true;
	}

	public String toString() {
		return beneficioDto.toString() + " - " + statusDto.toString() + " - "
				+ dataInicial + " - " + dataFinal;
	}
}
