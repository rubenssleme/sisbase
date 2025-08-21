package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.AbdbDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.ABDB;

public class TestesFabricaAbdb {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_abdb_converte_objeto_de_dominio_para_dto() {
		ABDB abdb = ABDB.AB;

		AbdbDTO abdbDTO = new FabricaAbdb().converterParaDTO(abdb);

		Assert.assertEquals(abdbDTO.toString(), abdb.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_abdb_converte_objeto_de_dto_para_dominio() {
		ABDB abdb = ABDB.DB;
		AbdbDTO abdbDto = new AbdbDTO(abdb.toString());

		ABDB abdbObtido = new FabricaAbdb().converterParaDominio(abdbDto);

		Assert.assertEquals(abdbObtido, abdb);
	}
}
