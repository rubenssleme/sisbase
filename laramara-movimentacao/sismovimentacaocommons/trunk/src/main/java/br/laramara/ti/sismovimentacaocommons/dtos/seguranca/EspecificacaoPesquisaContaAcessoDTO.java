package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaContaAcessoDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = -1464304022424189539L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.TODOS_CONTAS_ACESSO);
	}
}
