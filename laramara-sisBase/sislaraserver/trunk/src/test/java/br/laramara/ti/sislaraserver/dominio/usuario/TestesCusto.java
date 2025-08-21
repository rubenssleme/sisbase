package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoCusto;

public class TestesCusto {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void custo_validacao_com_erro_obrigatoriedade() {
		Custo custo = new Custo();
		custo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(custo.validado());
		Assert.assertEquals(custo.obterNumeroErros(), 2);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void custo_validacao_sem_erro_obrigatoriedade() {
		Custo custo = ContextoCusto.fabricarCustoComTodosOsDados();
		custo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(custo.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void custo_validacao_com_erro_de_valor() {
		Custo custo = ContextoCusto.fabricarCustoComTodosOsDados();
		custo.setValor("A123,A2");
		custo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(custo.validado());
		Assert.assertEquals(custo.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void custo_validacao_com_erro_de_valor_alternativo() {
		Custo custo = ContextoCusto.fabricarCustoComTodosOsDados();
		custo.setValor("123,2");
		custo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(custo.validado());
		Assert.assertEquals(custo.obterNumeroErros(), 1);
	}
}
