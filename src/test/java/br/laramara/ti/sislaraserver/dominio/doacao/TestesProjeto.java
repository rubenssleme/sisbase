package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoProjeto;

public class TestesProjeto {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_obrigatoriedade() {

		Projeto projeto = new Projeto();
		projeto.setNome(null);
		projeto.setDataInicial(null);
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 6);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Nome."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Data Inicial válida."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Adicionar o valor válido disponível para doação."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Adicionar outros valores válidos(ex. mão de obra)."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira o Valor de produtos."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira Outros Valores."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_data_invalida() {
		Projeto projeto = new Projeto();
		projeto.setDataInicial("laramara");
		projeto.setDataFinal("marvics");
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 7);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_data_termino_anteior_a_data_inicial() {
		Projeto projeto = new Projeto();
		projeto.setDataInicial("31/12/2012");
		projeto.setDataFinal("01/01/2012");
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 6);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_valor_total_produto_superior_valor_total_disponivel_para_produtos() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.setValorProdutos("1000,00");
		projeto.setValorOutros("1000,00");
		projeto.getLoteRecurso().get(0).setQuantidade("1000");
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 1);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Total de produtos não pode ser superior ao Valor dos produtos."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_lote_invalido() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		LoteRecurso loteRecurso = new LoteRecurso();
		loteRecurso.setQuantidade("kjasdkhad");
		loteRecurso.setRecurso(null);
		loteRecurso.setValor("87f9dh");
		projeto.setLoteRecurso(Arrays.asList(loteRecurso));
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 4);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Recurso."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Quantidade válida."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Valor válido."));
	}
		
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_sem_erro() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
				
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(projeto.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_tamanho_maximo() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.setNome(ContextoGenerico.obterGrande());
				
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(projeto.validado());
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Nome contendo até 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_soma_valor_total_com_sucesso() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.reservarParaDemanda(ContextoDemanda.fabricarDemandaComTodosOsDados());
			
		Assert.assertEquals(projeto.obterResumoReservas(), "Bengala - 80,00\n");
		Assert.assertEquals(projeto.obterSomaTotalProdutos(), "5000,00");
		Assert.assertEquals(projeto.getValorTotal(), "6100,00");
	}
}
