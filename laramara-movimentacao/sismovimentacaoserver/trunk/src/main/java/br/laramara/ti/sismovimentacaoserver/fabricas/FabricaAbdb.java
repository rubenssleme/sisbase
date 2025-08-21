package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.AbdbDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.ABDB;

public class FabricaAbdb extends FabricaBase<AbdbDTO, ABDB> {
	public final AbdbDTO converterParaDTO(ABDB abdb) {
		return abdb != null ? new AbdbDTO(abdb.toString()) : null;
	}

	public final ABDB converterParaDominio(AbdbDTO abdbDto) {
		return abdbDto != null ? ABDB.valueOf(abdbDto.toString()) : null;
	}
}
