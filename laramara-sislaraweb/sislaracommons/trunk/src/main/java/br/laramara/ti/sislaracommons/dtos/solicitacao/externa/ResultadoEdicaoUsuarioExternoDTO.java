package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoEdicaoUsuarioExternoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 198699348938457345L;

	public void efetuadoComSucesso() {
		super.efetuadoComSucesso("Edição de usuário externo realizada com sucesso.");
	}

}