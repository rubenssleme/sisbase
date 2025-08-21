package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.MecanismoTransferenciaDTO;

public class ResultadoDesativacaoFiltroGrupoDTO extends
		MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -9188481678259937461L;

	public void efetuadoComSucesso() {
		super.adicionarMensagem("A desativação do filtro de grupo foi realizada com sucesso.");
		super.efetuadoComSucesso();
	}
}
