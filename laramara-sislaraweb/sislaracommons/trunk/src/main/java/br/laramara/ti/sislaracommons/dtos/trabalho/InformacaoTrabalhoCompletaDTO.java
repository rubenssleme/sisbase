package br.laramara.ti.sislaracommons.dtos.trabalho;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;

public class InformacaoTrabalhoCompletaDTO extends ModeloDTO {

	private static final long serialVersionUID = 3646623378027337115L;

	private Long id;

	private String dataInicialVigencia;
	
	private String dataFinalVigencia;
	
	private LocalTrabalhoDTO localTrabalhoDto;

	private CargoDTO cargoDto;

	private SimNaoDTO voluntarioDto;
	
	private SimNaoDTO estagiarioDto;

	private SimNaoDTO encaminhadoPorLaramaraDto;
	
	private String obs;

	public InformacaoTrabalhoCompletaDTO(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTrabalhoDTO getLocalTrabalhoDto() {
		return localTrabalhoDto;
	}

	public void setLocalTrabalhoDto(LocalTrabalhoDTO localTrabalhoDto) {
		this.localTrabalhoDto = localTrabalhoDto;
	}

	public CargoDTO getCargoDto() {
		return cargoDto;
	}

	public void setCargoDto(CargoDTO cargoDto) {
		this.cargoDto = cargoDto;
	}

	public SimNaoDTO getVoluntarioDto() {
		return voluntarioDto;
	}

	public void setVoluntarioDto(SimNaoDTO voluntarioDto) {
		this.voluntarioDto = voluntarioDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public SimNaoDTO getEstagiarioDto() {
		return estagiarioDto;
	}

	public void setEstagiarioDto(SimNaoDTO estagiarioDto) {
		this.estagiarioDto = estagiarioDto;
	}

	public SimNaoDTO getEncaminhadoPorLaramaraDto() {
		return encaminhadoPorLaramaraDto;
	}

	public void setEncaminhadoPorLaramaraDto(SimNaoDTO encaminhadoPorLaramaraDto) {
		this.encaminhadoPorLaramaraDto = encaminhadoPorLaramaraDto;
	}

	public String getDataInicialVigencia() {
		return dataInicialVigencia;
	}

	public void setDataInicialVigencia(String dataInicialVigencia) {
		this.dataInicialVigencia = dataInicialVigencia;
	}

	public String getDataFinalVigencia() {
		return dataFinalVigencia;
	}

	public void setDataFinalVigencia(String dataFinalVigencia) {
		this.dataFinalVigencia = dataFinalVigencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacaoTrabalhoCompletaDTO other = (InformacaoTrabalhoCompletaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
