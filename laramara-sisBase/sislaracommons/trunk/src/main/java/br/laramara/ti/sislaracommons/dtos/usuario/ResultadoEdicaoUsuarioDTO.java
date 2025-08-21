package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoEdicaoUsuarioDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(UsuarioDTO objetoDtoEditado) {
		efetuadoComSucesso(DADOS_ARMAZENADOS_COM_SUCESSO + "Prontuario "
				+ objetoDtoEditado.getId(), objetoDtoEditado);
	}
}
