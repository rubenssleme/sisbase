package br.laramara.sistelemarketingserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesPermissao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissao_construido_com_sucesso() {
		Long id = new Long(12);
		String descricao = "Descrição";
		Permissao permissao = new Permissao();
		permissao.setId(id);
		permissao.setDescricao(descricao);

		Assert.assertEquals(permissao.getId(), id);
		Assert.assertEquals(permissao.getDescricao(), descricao);
	}
}
