package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.TipoApoioDTO;
import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;

public class FabricaTipoApoio extends FabricaBase<TipoApoioDTO, TipoApoio> {
	public final TipoApoioDTO converterParaDTO(TipoApoio tipoApoio) {
		return tipoApoio != null ? new TipoApoioDTO(tipoApoio.toString())
				: null;
	}

	public final TipoApoio converterParaDominio(TipoApoioDTO tipoApoio) {
		return tipoApoio != null ? TipoApoio.valueOf(TipoApoio.class,
				tipoApoio.toString()) : null;
	}
}
