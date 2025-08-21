package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoGeracaoAtendimentoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;
	
	private GrupoDTO grupoDto;
	private ModuloPeriodoDTO moduloPeriodoDTO;
	
	public void efetuadoComSucesso() {
		efetuadoComSucesso("Geração de atendimentos realizada com sucesso.",
				null);
	}

	public GrupoDTO getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDTO grupoDto) {
		this.grupoDto = grupoDto;
	}

	public ModuloPeriodoDTO getModuloPeriodoDTO() {
		return moduloPeriodoDTO;
	}

	public void setModuloPeriodoDTO(ModuloPeriodoDTO moduloPeriodoDTO) {
		this.moduloPeriodoDTO = moduloPeriodoDTO;
	}
}
