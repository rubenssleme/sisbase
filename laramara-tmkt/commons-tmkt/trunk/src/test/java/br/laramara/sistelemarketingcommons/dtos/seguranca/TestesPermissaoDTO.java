package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesPermissaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissao_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String descricao = "descricao";

		PermissaoDTO permissaoDto = new PermissaoDTO();
		permissaoDto.setId(id);
		permissaoDto.setDescricao(descricao);

		Assert.assertEquals(permissaoDto.getId(), id);
		Assert.assertEquals(permissaoDto.getDescricao(), descricao);
	}
}
