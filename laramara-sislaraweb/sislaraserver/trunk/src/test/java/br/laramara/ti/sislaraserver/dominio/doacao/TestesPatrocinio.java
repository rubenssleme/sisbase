package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoPatrocinio;

public class TestesPatrocinio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void patrocinio_validacao_com_erro_de_obrigatoriedade() {

		Patrocinio patrocinio = new Patrocinio();
		patrocinio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(patrocinio.validado());
		Assert.assertEquals(patrocinio.obterNumeroErros(), 2);
		Assert.assertTrue(patrocinio.obterDescricaoErros().contains("Insira uma Empresa."));
		Assert.assertTrue(patrocinio.obterDescricaoErros().contains("Insira um Valor de patrocínio válido."));
	}
	

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void patrocinio_validacao_com_erro_valor_doacao() {

		Patrocinio patrocinio = ContextoPatrocinio.criarPatrocinio();
		patrocinio.setValor("JHGJHS");
		patrocinio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(patrocinio.validado());
		Assert.assertEquals(patrocinio.obterNumeroErros(), 1);
		Assert.assertTrue(patrocinio.obterDescricaoErros().contains("Insira um Valor de patrocínio válido."));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void patrocinio_validacao_sem_erro() {
		Patrocinio patrocinio = ContextoPatrocinio.criarPatrocinio();
		patrocinio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(patrocinio.validado());
	}
}
