package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoConsultaCidDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso() {
		efetuadoComSucesso("Pesquisa de CID realizada com sucesso.", null);
	}
}
