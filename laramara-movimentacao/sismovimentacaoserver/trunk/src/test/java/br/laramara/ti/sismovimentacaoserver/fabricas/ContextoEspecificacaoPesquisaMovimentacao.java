package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;

public class ContextoEspecificacaoPesquisaMovimentacao {

	public static EspecificacaoPesquisaMovimentacaoDTO construirEspecificacaoPesquisaMovimentacaoDTO() {
		EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDTO = new EspecificacaoPesquisaMovimentacaoDTO();
		especificacaoPesquisaMovimentacaoDTO.setQuantidadePesquisa(2);
		return especificacaoPesquisaMovimentacaoDTO;
	}

	
}
