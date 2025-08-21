package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ItemCustoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;

public class TestesFabricaItemCusto {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_item_custo_converte_objeto_de_dominio_para_dto() {
		ItemCusto itemCusto = new ItemCusto(new Long(12222), "TERAPIA");

		ItemCustoDTO itemCustoDTO = new FabricaItemCusto()
				.converterParaDTO(itemCusto);

		Assert.assertEquals(itemCustoDTO.getId(), itemCusto.getId());
		Assert.assertEquals(itemCustoDTO.toString(), itemCusto.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_item_custo_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "MEDICAMENTOS";

		ItemCustoDTO itemCustoDto = new ItemCustoDTO(id, descricao);

		ItemCusto itemCustoObtido = new FabricaItemCusto()
				.converterParaDominio(itemCustoDto);

		Assert.assertEquals(itemCustoObtido.getDescricao(), descricao);
		Assert.assertEquals(itemCustoObtido.getId(), id);
	}
}
