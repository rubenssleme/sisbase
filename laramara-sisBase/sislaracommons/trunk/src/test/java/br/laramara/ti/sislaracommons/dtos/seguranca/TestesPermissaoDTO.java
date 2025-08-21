package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPermissaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissaodto_foi_construida_com_sucesso() {
		String descricao = "EDICAO_USUARIO";

		PermissaoDTO permissaoDto = new PermissaoDTO(descricao);

		Assert.assertEquals(permissaoDto.toString(), descricao);
	}
}
