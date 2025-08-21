package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ItemCustoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;

public class FabricaItemCusto extends FabricaBase<ItemCustoDTO, ItemCusto> {

	public final ItemCustoDTO converterParaDTO(ItemCusto itemCusto) {
		return itemCusto != null ? new ItemCustoDTO(itemCusto.getId(),
				itemCusto.getDescricao()) : null;
	}

	public final ItemCusto converterParaDominio(ItemCustoDTO itemCustoDto) {
		return itemCustoDto != null ? new ItemCusto(itemCustoDto.getId(),
				itemCustoDto.toString()) : null;
	}
}