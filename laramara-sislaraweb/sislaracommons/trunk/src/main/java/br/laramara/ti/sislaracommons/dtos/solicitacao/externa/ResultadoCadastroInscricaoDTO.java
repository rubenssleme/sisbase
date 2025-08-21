package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoCadastroInscricaoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 6409831196872761303L;

	public ResultadoCadastroInscricaoDTO() {
	}
	
	public void efetuadoComSucesso() {
		adicionarMensagem("Inscri��o realizada com sucesso.");
		super.efetuadoComSucesso();
	}
}
