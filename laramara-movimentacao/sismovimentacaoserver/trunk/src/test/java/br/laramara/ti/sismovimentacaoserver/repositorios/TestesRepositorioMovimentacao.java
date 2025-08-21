package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class TestesRepositorioMovimentacao extends TestesIntegracaoAbstrato {

	public TestesRepositorioMovimentacao() {
		super("DadosTestesRepositorioMovimentacao.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_movimentacao_pesquisa_todos_registros_apartir_especificacao() {
		EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao = new EspecificacaoPesquisaMovimentacao();
		especificacaoPesquisaMovimentacao.setQuantidadeResultado(1);

		RepositorioMovimentacao repositorio = Registro
				.obterRepositorioMovimentacao();

		List<Movimentacao> movimentacaoObtidas = repositorio
				.obterPor(especificacaoPesquisaMovimentacao);

		Assert.assertEquals(movimentacaoObtidas.size(), 1);
	}
}
