package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaraserver.dominio.SimNao;

public class FabricaSimNao extends FabricaBase<SimNaoDTO, SimNao> {
	public final SimNaoDTO converterParaDTO(SimNao simNao) {
		return simNao != null ? new SimNaoDTO(simNao.toString()) : null;
	}

	public final SimNao converterParaDominio(SimNaoDTO simNao) {
		return simNao != null ? SimNao.valueOf(SimNao.class, simNao.toString())
				: null;
	}
}
