package br.laramara.ti.sislaraserver.dominio.contribuicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoContribuinte;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;


public class TestesContribuinte {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contribuinte_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Contribuinte contribuinte = new Contribuinte();
		contribuinte.setEndereco(new Endereco());
		contribuinte.setValorContribuicao("XXX");
		contribuinte.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contribuinte.validado());
		Assert.assertNotNull(contribuinte.obterDescricaoErros());
		Assert.assertEquals(contribuinte.obterNumeroErros(), 9, "Deveria haver 9 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contribuinte_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		Contribuinte contribuinte = ContextoContribuinte.fabricarContribuinteComTodosOsDados();
		contribuinte.setNomeEmpresa(textoGrande);
		contribuinte.setCpf("40340dj");
		contribuinte.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contribuinte.validado());
		Assert.assertNotNull(contribuinte.obterDescricaoErros());
		Assert.assertTrue(
				contribuinte.obterDescricaoErros().contains("Insira a Nome/Empresa contendo até 100 caracteres."));
		Assert.assertTrue(
				contribuinte.obterDescricaoErros().contains("Insira um CPF válido."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contribuinte_validacao_sem_erro() {

		Contribuinte contribuite = ContextoContribuinte.fabricarContribuinteComTodosOsDados();
		contribuite.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(contribuite.validado());
	}
}
