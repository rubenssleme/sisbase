package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoModuloPeriodoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 4438233700108387388L;

	public void efetuadoComSucesso(ModuloPeriodoDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
