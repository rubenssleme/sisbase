package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;

public class TestesFabricaPermissao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_permissao_converte_objeto_de_dominio_para_dto() {
		Permissao permissao = Permissao.CONTA_ACESSO_EDICAO;

		PermissaoDTO permissaoDTO = new FabricaPermissao()
				.converterParaDTO(permissao);

		Assert.assertEquals(permissaoDTO.toString(), permissao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_permissao_converte_objeto_de_dto_para_dominio() {
		Permissao permissaoEsperado = Permissao.DESBLOQUEIO_TELA;
		PermissaoDTO permissaoDto = new PermissaoDTO(
				permissaoEsperado.toString());

		Permissao permissaoObtido = new FabricaPermissao()
				.converterParaDominio(permissaoDto);

		Assert.assertEquals(permissaoObtido.toString(),
				permissaoEsperado.toString());
	}
}
