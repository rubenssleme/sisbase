package br.laramara.ti.sislaraserver.dominio.familiar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesInformacaoTrabalho {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalho_validacao_sem_erro() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setFuncao("DESENVOLVEDOR");
		informacaoTrabalho.setEmpresa("PUC");
		informacaoTrabalho.setCargo(new Cargo(new Long(1), "Analista de SIstemas"));
		informacaoTrabalho.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(informacaoTrabalho.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalho_validacao_com_erro_obrigatoriedade() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(informacaoTrabalho.validado());
		Assert.assertEquals(informacaoTrabalho.obterNumeroErros(), 2);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalho_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setEmpresa(textoGrande);
		informacaoTrabalho.setFuncao(textoGrande);
		informacaoTrabalho.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(informacaoTrabalho.obterNumeroErros(), 2);
	}
}
