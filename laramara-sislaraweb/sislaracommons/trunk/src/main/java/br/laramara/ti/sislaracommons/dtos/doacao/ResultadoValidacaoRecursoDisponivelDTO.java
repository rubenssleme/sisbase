package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoRecursoDisponivelDTO extends ResultadoDTO {
	
	private static final long serialVersionUID = 4044169517070441109L;

	public void efetuadoComSucesso(RecursoDisponivelDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
