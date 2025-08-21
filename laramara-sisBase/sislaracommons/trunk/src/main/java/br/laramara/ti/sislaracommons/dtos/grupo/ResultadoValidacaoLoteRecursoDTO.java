package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoLoteRecursoDTO extends ResultadoDTO {
	
	private static final long serialVersionUID = 4044169517070441109L;

	public void efetuadoComSucesso(LoteRecursoDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
