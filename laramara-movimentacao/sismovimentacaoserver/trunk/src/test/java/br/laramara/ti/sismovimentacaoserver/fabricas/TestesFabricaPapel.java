package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.PapelDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Papel;

public class TestesFabricaPapel {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_papel_converte_objeto_de_dominio_para_dto() {
		Papel papel = Papel.MONO;

		PapelDTO papelDto = new FabricaPapel().converterParaDTO(papel);

		Assert.assertEquals(papelDto.toString(), papel.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_papel_converte_objeto_de_dto_para_dominio() {
		Papel papel = Papel.MONO;
		PapelDTO papelDto = new PapelDTO(papel.toString());

		Papel papelObtido = new FabricaPapel().converterParaDominio(papelDto);

		Assert.assertEquals(papelObtido, papel);
	}
}
