package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecursoDisponivel;

public class TestesRecursoDisponivel {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_disponivel_validacao_com_erro_obrigatoriedade() {
		RecursoDisponivel recursoDisponivel = new RecursoDisponivel();
		recursoDisponivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(recursoDisponivel.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_disponivel_validacao_com_erro_dados_invalidos() {
		RecursoDisponivel recursoDisponivel = new RecursoDisponivel();
		recursoDisponivel.setQuantidade("ABX");
		recursoDisponivel.setValorUnitario("BASB");
		recursoDisponivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(recursoDisponivel.obterNumeroErros(), 3);
		Assert.assertTrue(recursoDisponivel.obterErros().contains("Insira um Recurso."));
		Assert.assertTrue(recursoDisponivel.obterErros().contains("Insira uma Quantidade válida."));
		Assert.assertTrue(recursoDisponivel.obterErros().contains("Insira uma Valor válido."));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_disponivel_validacao_sem_erro_obrigatoriedade() {
		RecursoDisponivel recursoDisponivel = ContextoRecursoDisponivel
				.fabricarComTodosOsDados();

		recursoDisponivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(recursoDisponivel.validado());
		Assert.assertEquals(recursoDisponivel.obterValorTotal(), "300,00");
	}
}
