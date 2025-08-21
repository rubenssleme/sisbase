package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoArquivo;
import br.laramara.ti.sislaraserver.fabricas.ContextoDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoProjeto;

public class TestesProjeto {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_obrigatoriedade() {

		Projeto projeto = new Projeto();
		projeto.setNome(null);
		projeto.setDataInicialVigencia(null);
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 3);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Nome."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Data de vig�ncia v�lida."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Dura��o em meses v�lida."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_data_duracao_e_aditamento_invalidos_e_arquivos_duplicados() {
		Projeto projeto = new Projeto();
		projeto.setDataInicialVigencia("laramara");
		projeto.setDuracao("SJDH");
		projeto.setAditamento("KJHSD");
		projeto.setArquivos(ContextoArquivo.obterArquivosDuplicados());
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 6);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Dura��o v�lida."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Aditamento v�lido."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Existem arquivos de publica��o duplicados."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_valor_total_produto_superior_valor_total_disponivel_para_produtos() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.setValorProdutos("1000,00");
		projeto.setValorOutros("1000,00");
		projeto.getRecursosDisponiveis().get(0).setQuantidade("1000");
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		//TODO: REMOVER
		
		/*
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 1);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Total de produtos n�o pode ser superior ao Valor dos produtos."));
		*/
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_de_lote_invalido() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		RecursoDisponivel recursoDisponivel = new RecursoDisponivel();
		recursoDisponivel.setQuantidade("kjasdkhad");
		recursoDisponivel.setRecurso(null);
		recursoDisponivel.setValorUnitario("87f9dh");
		projeto.setRecursosDisponiveis(Arrays.asList(recursoDisponivel));
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(projeto.validado());
		Assert.assertEquals(projeto.obterNumeroErros(), 3);
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Recurso."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Quantidade v�lida."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Valor v�lido."));
	}
		
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_sem_erro() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
				
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(projeto.validado());
		Assert.assertEquals(projeto.getDataFinalVigencia(), "30/06/2013");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_validacao_com_erro_tamanho_maximo() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.setNome(ContextoGenerico.obterGrande());
		projeto.setEditalInvestimento(ContextoGenerico.obterGrande());
		projeto.setOrgaoParceiroFinanciador(ContextoGenerico.obterGrande());
		projeto.setLei(ContextoGenerico.obterGrande());
		projeto.setObjetivoGeral(ContextoGenerico.obterGrande());
		projeto.setPublicoAlvo(ContextoGenerico.obterGrande());
		projeto.setNumeroTermoFomentoParceria(ContextoGenerico.obterGrande());
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(projeto.validado());
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Nome contendo at� 100 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Edital de Investimento at� 100 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Org�o Parceiro/Financiador contendo at� 100 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira uma Lei contendo at� 100 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um Objetivo Geral contendo at� 20000 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um P�blico Alvo contendo at� 20000 caracteres."));
		Assert.assertTrue(projeto.obterDescricaoErros().contains("Insira um N�mero de Termo de Fomento/Parceria contendo at� 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projeto_soma_valor_total_com_sucesso() {
		Projeto projeto = ContextoProjeto.
				fabricarProjetoComTodosOsDados();
		projeto.reservarParaDemanda(ContextoDemanda.fabricarDemandaComTodosOsDados());
			
		//Assert.assertEquals(projeto.obterResumoReservas(), "Bengala - 80,00\n");
		//Assert.assertEquals(projeto.obterSomaTotalProdutos(), "5000,00");
		Assert.assertEquals(projeto.getValorTotal(), "6100,00");
	}
}
