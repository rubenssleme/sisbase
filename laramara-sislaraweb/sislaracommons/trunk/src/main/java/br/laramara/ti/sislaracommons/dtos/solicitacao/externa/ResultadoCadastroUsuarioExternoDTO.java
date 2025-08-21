package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoCadastroUsuarioExternoDTO extends ResultadoDTO {
	private static final long serialVersionUID = -8493992646502735208L;

	public ResultadoCadastroUsuarioExternoDTO() {
	}

	public void efetuadoComSucesso() {
		adicionarMensagem("Cadastro realizado com sucesso. Link de confirmação enviado para o seu e-mail.");
		super.efetuadoComSucesso();
	}

}
