package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.MecanismoTransferenciaDTO;

public class ResultadoAlteracaoExtensaoRelatorioDTO extends
		MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -9188481678259937461L;

	public void efetuadoComSucesso() {
		super.adicionarMensagem("Opera��o realizada com sucesso.");
		super.efetuadoComSucesso();
	}
}
