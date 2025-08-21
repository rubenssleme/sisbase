package br.laramara.ti.sismovimentacaoserver.dominio.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.repositorios.EspecificacaoPesquisaMovimentacao;

public class TestesEspecificacaoPesquisaMovimentacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_movimentacao_valida_com_sucesso() {

		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = new EspecificacaoPesquisaMovimentacao();
		especificacaoPesquisaMovimentacao.setQuantidadeResultado(23);
		especificacaoPesquisaMovimentacao
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(especificacaoPesquisaMovimentacao.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_movimentacao_nao_valida_com_sucesso() {

		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = new EspecificacaoPesquisaMovimentacao();
		especificacaoPesquisaMovimentacao
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacaoPesquisaMovimentacao.validado());
	}
}
