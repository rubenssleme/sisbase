package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public abstract class ModuloRelacaoBaseDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 5146302654585940710L;

	private Long id;
	private String dataInicio;
	private String dataOcorrencia;
	private StatusRelacaoComModuloDTO statusDto;
	private boolean aprovado;
	private boolean ignorarRegraDeReuniaoDeIntegracao;
	private String obs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isIgnorarRegraDeReuniaoDeIntegracao() {
		return ignorarRegraDeReuniaoDeIntegracao;
	}

	public void setIgnorarRegraDeReuniaoDeIntegracao(boolean ignorarRegraDeReuniaoDeIntegracao) {
		this.ignorarRegraDeReuniaoDeIntegracao = ignorarRegraDeReuniaoDeIntegracao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public StatusRelacaoComModuloDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusRelacaoComModuloDTO statusDto) {
		this.statusDto = statusDto;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@Override
	public String toString() {
		return obterNome();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloRelacaoBaseDTO other = (ModuloRelacaoBaseDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	protected abstract String obterNome();
}
