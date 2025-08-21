package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoLeituraDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoLeitura;

public class FabricaTipoLeitura extends
		FabricaBase<TipoLeituraDTO, TipoLeitura> {
	public final TipoLeituraDTO converterParaDTO(TipoLeitura tipoLeitura) {
		return tipoLeitura != null ? new TipoLeituraDTO(tipoLeitura.toString())
				: null;
	}

	public final TipoLeitura converterParaDominio(TipoLeituraDTO tipoLeitura) {
		return tipoLeitura != null ? TipoLeitura.valueOf(TipoLeitura.class,
				tipoLeitura.toString()) : null;
	}
}
