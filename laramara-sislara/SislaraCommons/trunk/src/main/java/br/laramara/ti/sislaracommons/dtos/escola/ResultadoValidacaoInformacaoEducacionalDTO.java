package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoInformacaoEducacionalDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8189620152330934777L;

	public void efetuadoComSucesso(InformacaoEducacionalDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}
