package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PeriodoDeficienciaDTO extends ModeloDTO {
	
	private static final long serialVersionUID = -9143396164202455424L;
	
	private Long id;
	private DeficienciaDTO deficienciaDto;
	private CidDTO cidDto;
	private EpocaIncidenciaDTO epocaIncidenciaDto;
	private String dataInicial;
	private String dataFinal;
	private List<EtiologiaDTO> etiologiasDto;
	
	public PeriodoDeficienciaDTO() {
		etiologiasDto = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public DeficienciaDTO getDeficienciaDto() {
		return deficienciaDto;
	}

	public CidDTO getCidDto() {
		return cidDto;
	}

	public void setCidDto(CidDTO cidDto) {
		this.cidDto = cidDto;
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

	public void setDeficienciaDto(DeficienciaDTO deficienciaDto) {
		this.deficienciaDto = deficienciaDto;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<EtiologiaDTO> getEtiologiasDto() {
		return etiologiasDto;
	}

	public void setEtiologiasDto(List<EtiologiaDTO> etiologiasDto) {
		this.etiologiasDto = etiologiasDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoDeficienciaDTO other = (PeriodoDeficienciaDTO) obj;
		if (deficienciaDto == null) {
			if (other.deficienciaDto != null)
				return false;
		} else if (!deficienciaDto.equals(other.deficienciaDto))
			return false;
		return true;
	}

	public String toString() {
		return deficienciaDto.toString() + " - "
				+ (cidDto != null ? cidDto : "Sem CID") + " - "
				+ (epocaIncidenciaDto != null ? epocaIncidenciaDto : "Sem Época")
				+ " - " + dataInicial + " - " + dataFinal
				+ (!etiologiasDto.isEmpty() ? " - Etiologias " + etiologiasDto : "Sem Etiologia");
	}
}
