package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Custo;

public class FabricaCusto extends FabricaBase<CustoDTO, Custo> {

	public final CustoDTO converterParaDTO(Custo custo) {
		CustoDTO custoDTO = new CustoDTO();
		custoDTO.setId(custo.getId());
		custoDTO.setItemCustoDto(new FabricaItemCusto().converterParaDTO(custo
				.getItemCusto()));
		custoDTO.setValor(custo.getValor());
		return custoDTO;
	}

	public final Custo converterParaDominio(CustoDTO custoDto) {
		Custo custo = new Custo();
		if (custoDto.getId() != null) {
			custo.setId(custoDto.getId());
		}
		custo.setItemCusto(new FabricaItemCusto().converterParaDominio(custoDto
				.getItemCustoDto()));
		custo.setValor(custoDto.getValor());
		return custo;
	}
}
