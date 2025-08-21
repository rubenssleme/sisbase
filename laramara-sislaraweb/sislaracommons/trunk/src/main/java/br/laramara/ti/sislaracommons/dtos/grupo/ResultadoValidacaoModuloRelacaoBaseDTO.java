package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoModuloRelacaoBaseDTO extends ResultadoDTO {

	private static final long serialVersionUID = 4438233700108387388L;

	public void efetuadoComSucesso(ModuloRelacaoBaseDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados alterados com sucesso.", objetoDtoEditado);
	}
}
