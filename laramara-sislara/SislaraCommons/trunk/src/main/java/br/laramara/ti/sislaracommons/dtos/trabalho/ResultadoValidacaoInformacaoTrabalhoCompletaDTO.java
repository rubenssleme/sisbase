package br.laramara.ti.sislaracommons.dtos.trabalho;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoInformacaoTrabalhoCompletaDTO extends
		ResultadoDTO {

	private static final long serialVersionUID = 8189620152330934777L;

	public void efetuadoComSucesso(
			InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto) {
		efetuadoComSucesso("Dados adicionados com sucesso.",
				informacaoTrabalhoCompletaDto);
	}
}
