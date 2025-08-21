package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoEdicaoContribuinteDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(ContribuinteDTO objetoDtoEditado) {
		efetuadoComSucesso(DADOS_ARMAZENADOS_COM_SUCESSO, objetoDtoEditado);
	}
}
