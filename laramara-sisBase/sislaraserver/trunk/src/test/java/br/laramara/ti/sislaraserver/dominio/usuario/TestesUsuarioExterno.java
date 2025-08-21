package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuarioExterno;

public class TestesUsuarioExterno {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_validacao_sem_erro() {

		UsuarioExterno usuarioExterno = ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados();
		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuarioExterno.validado());
	}


	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_validacao_com_erro_de_obrigatoriedade_de_dados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();
		
		usuarioExterno.setEmail("");

		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuarioExterno.validado());
		Assert.assertNotNull(usuarioExterno.obterDescricaoErros());
		Assert.assertEquals(usuarioExterno.obterNumeroErros(), 1,
				"Deveria haver 1 infração de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_validacao_com_erro_de_tamanho_de_dados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setEmail(ContextoGenerico.obterGrande() + "@gmail.com");
		
		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuarioExterno.validado());
		Assert.assertNotNull(usuarioExterno.obterDescricaoErros());
		Assert.assertEquals(usuarioExterno.obterNumeroErros(), 1,
				"Deveriam haver 1 infração de largura máxima de dados.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_anula_campo_em_branco() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setEmail("");

		Assert.assertNull(usuarioExterno.getEmail());
	}

}
