package br.laramara.sistelemarketingserver.dominio.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoGenerico;
import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;
import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;

public class TestesNivel {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_construido_com_sucesso() {
		Long id = new Long(12);
		String descricao = "Descrição";
		List<Permissao> permissoes = Arrays.asList(new Permissao());
		Nivel nivel = new Nivel();
		nivel.setId(id);
		nivel.setDescricao(descricao);
		nivel.setPermissoes(permissoes);
		nivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(nivel.validado());
		Assert.assertEquals(nivel.getId(), id);
		Assert.assertEquals(nivel.getDescricao(), descricao);
		Assert.assertEquals(nivel.getPermissoes().size(), permissoes.size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_com_erro_de_obrigatoriedade() {
		Nivel nivel = new Nivel();
		nivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(nivel.validado());
		Assert.assertTrue(nivel.obterDescricaoErros().contains("Insira um nível."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_com_erro_de_tamanho_maximo() {
		Nivel nivel = ContextoNivel.fabricarNivel();
		nivel.setDescricao(ContextoGenerico.obterGrande());
		nivel.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(nivel.validado());
		Assert.assertTrue(nivel.obterDescricaoErros().contains("Insira um nível contendo até 100 caracteres."));
	}
}
