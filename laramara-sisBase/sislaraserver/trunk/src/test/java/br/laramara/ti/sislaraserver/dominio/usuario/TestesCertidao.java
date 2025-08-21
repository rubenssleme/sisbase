package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoCertidao;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesCertidao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void certidao_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Certidao certidao = new Certidao();

		certidao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(certidao.validado());
		Assert.assertNotNull(certidao.obterDescricaoErros());
		Assert.assertEquals(certidao.obterNumeroErros(), 7,
				"Deveria haver 7 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void certidao_validacao_com_erro_de_tamanho_maximo_de_dados() {
		Certidao certidao = ContextoCertidao.fabricarCertidaoComTodosOsDados();
		certidao.setDistrito(ContextoGenerico.obterGrande());
		certidao.setNumero(ContextoGenerico.obterGrande());
		certidao.setFolha(ContextoGenerico.obterGrande());
		certidao.setLivro(ContextoGenerico.obterGrande());
		
		certidao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(certidao.validado());
		Assert.assertNotNull(certidao.obterDescricaoErros());
		Assert.assertEquals(certidao.obterNumeroErros(), 4,
				"Deveria haver 4 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void certidao_validacao_sem_erro() {
		Certidao certidao = ContextoCertidao.fabricarCertidaoComTodosOsDados();

		certidao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(certidao.validado());
		Assert.assertTrue(certidao.obterDescricaoErros().isEmpty());
	}
}
