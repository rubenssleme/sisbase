package br.laramara.ti.sismovimentacaoserver.dominio.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;

public class TestesMovimentacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacao_status_inicial_com_sucesso() {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDataCriacao("31/12/2010", new ContaAcesso());

		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		Assert.assertTrue(movimentacao.validado());
		Assert.assertEquals(movimentacao.obterStatusAtual(),
				Status.ENTRADA_DO_ARQUIVO);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacao_proximo_status_com_sucesso() {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDataCriacao("31/12/2010", new ContaAcesso());
		movimentacao.avancarHistoricoStatus("31/12/2012", new ContaAcesso());

		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		Assert.assertTrue(movimentacao.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacao_status_inicial_sem_sucesso() {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDataCriacao("sdsdds", new ContaAcesso());

		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		Assert.assertFalse(movimentacao.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacao_com_erros_obrigatoriedade_status() {

		Movimentacao movimentacao = new Movimentacao();

		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		Assert.assertFalse(movimentacao.validado());
		Assert.assertEquals(movimentacao.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacao_status_inicial_sem_sucesso_por_transicao_invalida_status() {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDataCriacao("31/12/2010", new ContaAcesso());
		movimentacao.avancarHistoricoStatus("31/12/2012", new ContaAcesso());
		movimentacao.avancarHistoricoStatus("31/12/2012", new ContaAcesso());
		movimentacao.avancarHistoricoStatus("31/12/2012", new ContaAcesso());
		movimentacao.avancarHistoricoStatus("31/12/2012", new ContaAcesso());
		movimentacao.adicionarStatus(Status.APROVADO_AGUARDANDO_ESPECIFICACAO,
				"31/12/2012", new ContaAcesso());

		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		Assert.assertFalse(movimentacao.validado());
		Assert.assertFalse(movimentacao.obterErros().isEmpty());
	}

}