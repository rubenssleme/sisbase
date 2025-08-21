package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;

public class TestesFabricaPermissao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_permissao_converte_objeto_de_dominio_para_dto() {
		Permissao permissao = Permissao.INSTITUICAO_TELA_EDICAO_VISUALIZAR;

		PermissaoDTO permissaoDTO = new FabricaPermissao()
				.converterParaDTO(permissao);

		Assert.assertEquals(permissaoDTO.toString(), permissao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_permissao_converte_objeto_de_dto_para_dominio() {
		Permissao permissaoEsperado = Permissao.AGENDA_EDICAO;
		PermissaoDTO permissaoDto = new PermissaoDTO(
				permissaoEsperado.toString());

		Permissao permissaoObtido = new FabricaPermissao()
				.converterParaDominio(permissaoDto);

		Assert.assertEquals(permissaoObtido.toString(),
				permissaoEsperado.toString());
	}
}
