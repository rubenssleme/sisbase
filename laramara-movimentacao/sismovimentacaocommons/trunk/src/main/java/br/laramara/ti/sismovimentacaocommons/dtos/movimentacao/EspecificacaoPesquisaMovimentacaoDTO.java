package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaMovimentacaoDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 4153187596713188405L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.QUANTIDADE);
	}

	public Integer getQuantidadeResultado() {
		return (Integer) obterParametro(ChavePesquisaDTO.QUANTIDADE);
	}

	public void setQuantidadePesquisa(Integer quantidadePesquisa) {
		adicionarParametro(ChavePesquisaDTO.QUANTIDADE, quantidadePesquisa);
	}
}
