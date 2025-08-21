package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.repositorios.EspecificacaoPesquisaMovimentacao;

public class TestesFabricaEspecificacaoPesquisaMovimentacao {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_movimentacao_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDto = ContextoEspecificacaoPesquisaMovimentacao
				.construirEspecificacaoPesquisaMovimentacaoDTO();

		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = new FabricaEspecificacaoPesquisaMovimentacao()
				.converterParaDominio(especificacaoPesquisaMovimentacaoDto);

		Assert.assertEquals(
				especificacaoPesquisaMovimentacao.getQuantidadeResultado(),
				especificacaoPesquisaMovimentacaoDto.getQuantidadeResultado());
	}
}
