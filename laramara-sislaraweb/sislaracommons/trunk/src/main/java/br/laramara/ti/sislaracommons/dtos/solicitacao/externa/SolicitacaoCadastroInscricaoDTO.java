package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;

public class SolicitacaoCadastroInscricaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 4691148243676230180L;

	private String token;
	private InscricaoDTO inscricaoDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public InscricaoDTO getInscricaoDto() {
		return inscricaoDto;
	}

	public void setInscricaoDto(InscricaoDTO inscricaoDto) {
		this.inscricaoDto = inscricaoDto;
	}

	@Override
	public String toString() {
		return "SolicitacaoCadastroInscricaoDTO [token=" + token + ", inscricaoDto=" + inscricaoDto + "]";
	}

}
