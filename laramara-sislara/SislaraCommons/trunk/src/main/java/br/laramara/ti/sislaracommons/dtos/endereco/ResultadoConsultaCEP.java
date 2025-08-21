package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoConsultaCEP extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso() {
		efetuadoComSucesso(
				"Pesquisa de Endereço por CEP realizada com sucesso.", null);
	}
}
