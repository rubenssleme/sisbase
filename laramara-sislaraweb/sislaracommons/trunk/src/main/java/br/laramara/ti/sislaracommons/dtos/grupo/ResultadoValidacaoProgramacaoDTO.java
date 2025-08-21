package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoProgramacaoDTO extends ResultadoDTO{

	private static final long serialVersionUID = -6138234174115569601L;
	
	public void efetuadoComSucesso(ProgramacaoDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
