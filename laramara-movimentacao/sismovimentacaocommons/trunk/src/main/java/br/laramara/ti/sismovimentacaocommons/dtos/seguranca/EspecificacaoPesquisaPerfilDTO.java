package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaPerfilDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 697291943184587425L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.TODOS_PERFIS);
	}
}
