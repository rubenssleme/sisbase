package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.MecanismoTransferenciaDTO;

public class ResultadoSolicitacaoRecuperacaoSenhaDTO extends MecanismoTransferenciaDTO {

	private static final long serialVersionUID = 1398687815099789099L;

	public ResultadoSolicitacaoRecuperacaoSenhaDTO() {
	}

	public void efetuadoComSucesso() {
		adicionarMensagem("Link de alteração de senha enviado para o email com sucesso.");
		super.efetuadoComSucesso();
	}

}
