package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoPermissao;
import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;
import br.laramara.sistelemarketingserver.fabricas.PermissaoFabrica;

public class TestesPermissaoFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_permissao_converte_objeto_de_dominio_para_dto() {

		Permissao permissao = ContextoPermissao.fabricarPermissao();

		PermissaoDTO permissaoDtoCovertido = new PermissaoFabrica().converterParaDTO(permissao);

		Assert.assertEquals(permissaoDtoCovertido.getId(), permissao.getId());
		Assert.assertEquals(permissaoDtoCovertido.getDescricao(), permissao.getDescricao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_permissao_converte_objeto_dto_para_dominio() {
		PermissaoDTO permissaoDto = ContextoPermissao.fabricarPermissaoDto();

		Permissao permissaoCovertido = new PermissaoFabrica().converterParaDominio(permissaoDto);

		Assert.assertEquals(permissaoCovertido.getId(), permissaoDto.getId());
		Assert.assertEquals(permissaoCovertido.getDescricao(), permissaoDto.getDescricao());
	}
}
