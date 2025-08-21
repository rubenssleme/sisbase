package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PeriodoComprometimentoDTO extends ModeloDTO{

	private static final long serialVersionUID = -9143396164202455424L;

	private Long id;
	private ComprometimentoDTO comprometimentoDto;
	private EpocaIncidenciaDTO epocaIncidenciaDto;
	private String dataInicial;
	private String dataFinal;

	public PeriodoComprometimentoDTO() {
	}

	public Long getId() {
		return id;
	}

	public EpocaIncidenciaDTO getEpocaIncidenciaDto() {
		return epocaIncidenciaDto;
	}

	public void setEpocaIncidenciaDto(EpocaIncidenciaDTO epocaIncidenciaDto) {
		this.epocaIncidenciaDto = epocaIncidenciaDto;
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

	public void setComprometimentoDto(ComprometimentoDTO comprometimentoDto) {
		this.comprometimentoDto = comprometimentoDto;
	}
	
	public ComprometimentoDTO getComprometimentoDto() {
		return comprometimentoDto;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoComprometimentoDTO other = (PeriodoComprometimentoDTO) obj;
		if (comprometimentoDto == null) {
			if (other.comprometimentoDto != null)
				return false;
		} else if (!comprometimentoDto.equals(other.comprometimentoDto))
			return false;
		return true;
	}

	public String toString() {
		return comprometimentoDto.toString() + " - " 
				+ (epocaIncidenciaDto != null ? epocaIncidenciaDto : "Sem Época") + " - " + dataInicial + " - "
				+ dataFinal;
	}
}
