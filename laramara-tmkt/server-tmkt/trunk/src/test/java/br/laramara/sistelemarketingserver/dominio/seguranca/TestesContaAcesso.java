package br.laramara.sistelemarketingserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoGenerico;

public class TestesContaAcesso {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_construido_com_sucesso() {
		Long id = new Long(12);
		String login  = "paulo.bandeira";
		String senha = "1234";
		String senhaAposMD5 = "81dc9bdb52d04dc20036dbd8313ed055";
		String nome = "Paulo Augusto";
		
		boolean ativo = true;
		Nivel nivel = ContextoNivel.fabricarNivel();

		ContaAcesso contaAcesso = new ContaAcesso();
		contaAcesso.setId(id);
		contaAcesso.setNome(nome);
		contaAcesso.setLogin(login);
		contaAcesso.setSenha(senha);
		contaAcesso.setAtivo(ativo);
		contaAcesso.setNivel(nivel);
		contaAcesso.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(contaAcesso.validado());
		Assert.assertEquals(contaAcesso.getId(), id);
		Assert.assertEquals(contaAcesso.getNome(), nome);
		Assert.assertEquals(contaAcesso.getLogin(), login);
		Assert.assertEquals(contaAcesso.getSenha(), senhaAposMD5);
		Assert.assertEquals(contaAcesso.isAtivo(), ativo);
		Assert.assertEquals(contaAcesso.getNivel().getId(), nivel.getId());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_com_erro_de_obrigatoriedade() {
		ContaAcesso contaAcesso = new ContaAcesso();
		contaAcesso.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(contaAcesso.validado());
		Assert.assertTrue(contaAcesso.obterDescricaoErros().contains("Insira um login válido."));
		Assert.assertTrue(contaAcesso.obterDescricaoErros().contains("Insira um nome válido."));
		Assert.assertTrue(contaAcesso.obterDescricaoErros().contains("Insira uma senha válida."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_com_erro_de_tamanho_maximo() {
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarContaAcessoCarlos();
		contaAcesso.setNome(ContextoGenerico.obterGrande());
		contaAcesso.setLogin(ContextoGenerico.obterGrande());
		contaAcesso.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(contaAcesso.validado());
		Assert.assertTrue(contaAcesso.obterDescricaoErros().contains("Insira um login contendo até 100 caracteres."));
		Assert.assertTrue(contaAcesso.obterDescricaoErros().contains("Insira um nome contendo até 100 caracteres."));
	}
}
