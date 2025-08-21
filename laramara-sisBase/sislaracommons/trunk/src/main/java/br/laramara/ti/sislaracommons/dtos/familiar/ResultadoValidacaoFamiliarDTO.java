package br.laramara.ti.sislaracommons.dtos.familiar;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoFamiliarDTO extends ResultadoDTO {

	private static final long serialVersionUID = 4438233700108387388L;

	public void efetuadoComSucesso(FamiliarDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
