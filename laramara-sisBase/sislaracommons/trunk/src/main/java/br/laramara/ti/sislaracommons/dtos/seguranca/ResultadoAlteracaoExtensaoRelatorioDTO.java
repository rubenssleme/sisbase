package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.MecanismoTransferenciaDTO;

public class ResultadoAlteracaoExtensaoRelatorioDTO extends
		MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -9188481678259937461L;

	public void efetuadoComSucesso() {
		super.adicionarMensagem("Operação realizada com sucesso.");
		super.efetuadoComSucesso();
	}
}
