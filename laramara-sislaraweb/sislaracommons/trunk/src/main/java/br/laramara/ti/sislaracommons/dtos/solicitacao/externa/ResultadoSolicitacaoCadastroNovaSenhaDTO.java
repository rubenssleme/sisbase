package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoSolicitacaoCadastroNovaSenhaDTO extends ResultadoDTO {
	private static final long serialVersionUID = 1L;

	public void efetuadoComSucesso() {
		super.efetuadoComSucesso("Cadastro de nova senha realizado com sucesso.");
	}
}
