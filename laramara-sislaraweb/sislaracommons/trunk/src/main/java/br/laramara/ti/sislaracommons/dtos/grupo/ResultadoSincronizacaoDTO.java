package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoSincronizacaoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso() {
		efetuadoComSucesso(
				"Sincronização de Profissionais realizada com sucesso.", null);
	}
}
