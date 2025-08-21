package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;

public class ResultadoGeracaoAtendimentoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;
	
	private GrupoDTO grupoDto;
	private AtendimentoGrupoDTO atendimentoGrupoGeradoDTO;
	
	public void efetuadoComSucesso() {
		efetuadoComSucesso("Geração de atendimentos realizada com sucesso.",
				null);
	}

	public AtendimentoGrupoDTO getAtendimentoGrupoGeradoDTO() {
		return atendimentoGrupoGeradoDTO;
	}

	public void setAtendimentoGrupoGeradoDTO(AtendimentoGrupoDTO atendimentoGrupoGeradoDTO) {
		this.atendimentoGrupoGeradoDTO = atendimentoGrupoGeradoDTO;
	}

	public GrupoDTO getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDTO grupoDto) {
		this.grupoDto = grupoDto;
	}
}
