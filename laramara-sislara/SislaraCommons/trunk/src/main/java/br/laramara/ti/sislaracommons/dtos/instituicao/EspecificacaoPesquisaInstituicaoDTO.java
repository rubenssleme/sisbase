package br.laramara.ti.sislaracommons.dtos.instituicao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaInstituicaoDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 697291943184587425L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.NOME_INSTITUICAO);
	}
}
