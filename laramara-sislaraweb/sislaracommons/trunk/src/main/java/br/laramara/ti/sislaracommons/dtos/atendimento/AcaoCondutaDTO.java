package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;

public class AcaoCondutaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 3706853722766554441L;

	private Long id;

	private TipoAcaoCondutaDTO tipoAcaoCondutaDto;

	private GrupoDTO grupoDto;
	
	private String dataExpectativa;
	
	private SetorDTO setorDto;
	
	private String obs;
	
	private String dataProcessamento;
	
	private String resultadoProcessamento;
	
	private boolean cancelada;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAcaoCondutaDTO getTipoAcaoCondutaDto() {
		return tipoAcaoCondutaDto;
	}

	public void setTipoAcaoCondutaDto(TipoAcaoCondutaDTO tipoAcaoCondutaDto) {
		this.tipoAcaoCondutaDto = tipoAcaoCondutaDto;
	}

	public GrupoDTO getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDTO grupoDto) {
		this.grupoDto = grupoDto;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getResultadoProcessamento() {
		return resultadoProcessamento;
	}

	public void setResultadoProcessamento(String resultadoProcessamento) {
		this.resultadoProcessamento = resultadoProcessamento;
	}

	public String getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	
	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	public String getDataExpectativa() {
		return dataExpectativa;
	}

	public void setDataExpectativa(String dataExpectativa) {
		this.dataExpectativa = dataExpectativa;
	}
	
	public SetorDTO getSetorDto() {
		return setorDto;
	}

	public void setSetorDto(SetorDTO setorDto) {
		this.setorDto = setorDto;
	}

	@Override
	public String toString() {
		return tipoAcaoCondutaDto 
				+ (grupoDto != null ? " no " + grupoDto.obterNome() + ". " : "") 
				+ (!obs.isEmpty()  ? " Obs: " + obs : "") 
				+ (!dataProcessamento.isEmpty()  ? " Data de processamento: " + dataProcessamento : "")
				+ (!resultadoProcessamento.isEmpty() ? " Resultado de Processamento: " + resultadoProcessamento : "")
				+ " Cancelada: " + (cancelada ? "SIM" : "NÃO");
	}
}
