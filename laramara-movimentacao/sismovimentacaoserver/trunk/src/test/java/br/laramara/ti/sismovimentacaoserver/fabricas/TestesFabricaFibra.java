package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.FibraDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Fibra;

public class TestesFabricaFibra {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_fibra_converte_objeto_de_dominio_para_dto() {
		Fibra fibra = Fibra.HORIZONTAL;

		FibraDTO fibraDTO = new FabricaFibra().converterParaDTO(fibra);

		Assert.assertEquals(fibraDTO.toString(), fibra.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_fibra_converte_objeto_de_dto_para_dominio() {
		Fibra fibra = Fibra.HORIZONTAL;
		FibraDTO fibraDto = new FibraDTO(Fibra.HORIZONTAL.toString());

		Fibra fibraObtido = new FabricaFibra().converterParaDominio(fibraDto);

		Assert.assertEquals(fibraObtido, fibra);
	}
}
