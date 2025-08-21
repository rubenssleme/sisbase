package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.FibraDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Fibra;

public class FabricaFibra extends FabricaBase<FibraDTO, Fibra> {
	public final FibraDTO converterParaDTO(Fibra fibra) {
		return fibra != null ? new FibraDTO(fibra.toString()) : null;
	}

	public final Fibra converterParaDominio(FibraDTO fibraDto) {
		return fibraDto != null ? Fibra.valueOf(fibraDto.toString()) : null;
	}
}
