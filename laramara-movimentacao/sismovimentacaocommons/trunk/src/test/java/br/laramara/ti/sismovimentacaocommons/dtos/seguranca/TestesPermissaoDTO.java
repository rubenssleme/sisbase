package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesPermissaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissaodto_foi_construida_com_sucesso() {
		String descricao = "CONTA_ACESSO_EDICAO";

		PermissaoDTO permissaoDto = new PermissaoDTO(descricao);

		Assert.assertEquals(permissaoDto.toString(), descricao);
	}
}
