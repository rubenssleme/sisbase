package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.MecanismoTransferenciaDTO;

public class ResultadoDesativacaoFiltroGrupoDTO extends
		MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -9188481678259937461L;

	public void efetuadoComSucesso() {
		super.adicionarMensagem("A desativação do filtro de grupo foi realizada com sucesso.");
		super.efetuadoComSucesso();
	}
}
