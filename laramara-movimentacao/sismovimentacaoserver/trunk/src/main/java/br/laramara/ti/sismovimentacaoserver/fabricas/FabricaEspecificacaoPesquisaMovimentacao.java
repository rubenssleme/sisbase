package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaoserver.repositorios.EspecificacaoPesquisaMovimentacao;

public class FabricaEspecificacaoPesquisaMovimentacao
		extends
		FabricaBase<EspecificacaoPesquisaMovimentacaoDTO, EspecificacaoPesquisaMovimentacao> {

	@Override
	public EspecificacaoPesquisaMovimentacaoDTO converterParaDTO(
			EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaMovimentacao converterParaDominio(
			EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDto) {
		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = null;
		if (especificacaoPesquisaMovimentacaoDto != null) {
			especificacaoPesquisaMovimentacao = new EspecificacaoPesquisaMovimentacao();
		}
		especificacaoPesquisaMovimentacao
				.setQuantidadeResultado(especificacaoPesquisaMovimentacaoDto
						.getQuantidadeResultado());

		return especificacaoPesquisaMovimentacao;
	}

}
