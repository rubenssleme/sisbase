package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoConsultaPerfilPreenchidoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 1315983842956403305L;

	private boolean perfilPreenchido;

	public void efetuadoComSucesso(boolean perfilPreenchido) {
		super.efetuadoComSucesso();
		setPerfilPreenchido(perfilPreenchido);
	}

	public void setPerfilPreenchido(boolean perfilPreenchido) {
		this.perfilPreenchido = perfilPreenchido;
	}

	public boolean isPerfilPreenchido() {
		return perfilPreenchido;
	}

}
