package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.SimNaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.SimNao;

public class FabricaSimNao extends FabricaBase<SimNaoDTO, SimNao> {
	public final SimNaoDTO converterParaDTO(SimNao simNao) {
		return simNao != null ? new SimNaoDTO(simNao.toString()) : null;
	}

	public final SimNao converterParaDominio(SimNaoDTO simNaoDto) {
		return simNaoDto != null ? SimNao.valueOf(simNaoDto.toString()) : null;
	}
}
