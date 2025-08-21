package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecibo;

public class TestesRecibo {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibo_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Recibo contribuinte = new Recibo();
		contribuinte.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contribuinte.validado());
		Assert.assertNotNull(contribuinte.obterDescricaoErros());
		Assert.assertEquals(contribuinte.obterNumeroErros(), 5, "Deveria haver 5 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibo_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		Recibo recibo = ContextoRecibo.fabricarRecibo();
		recibo.setDescricao(textoGrande);
		recibo.setMotivoDoCancelamento(textoGrande);
		recibo.setNome(textoGrande);
		recibo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(recibo.validado());
		Assert.assertNotNull(recibo.obterDescricaoErros());
		Assert.assertTrue(
				recibo.obterDescricaoErros().contains("Insira um nome contendo até 100 caracteres."));
		Assert.assertTrue(
				recibo.obterDescricaoErros().contains("Insira uma descrição contendo até 20000 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibo_a_cancelar_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		Recibo recibo = ContextoRecibo.fabricarRecibo();
		recibo.cancelarSePossivel(textoGrande, ContaAcesso.obterAcessoRoot());

		Assert.assertFalse(recibo.validado());
		Assert.assertNotNull(recibo.obterDescricaoErros());
		Assert.assertTrue(
				recibo.obterDescricaoErros().contains("Insira um motivo de cancelamento contendo até 20000 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibo_normal_validacao_sem_erro() {
		Recibo recibo = ContextoRecibo.fabricarRecibo();
		recibo.setId(null);
		recibo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(recibo.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibo_validacao_sem_erro() {
		Recibo recibo = ContextoRecibo.fabricarRecibo();
		recibo.setId(null);
		recibo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(recibo.validado());
	}
}
