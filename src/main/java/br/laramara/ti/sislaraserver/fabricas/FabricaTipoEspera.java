package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;

public class FabricaTipoEspera extends FabricaBase<TipoEsperaDTO, TipoEspera> {
	public final TipoEsperaDTO converterParaDTO(TipoEspera tipo) {
		return tipo != null ? new TipoEsperaDTO(tipo.toString()) : null;
	}

	public final TipoEspera converterParaDominio(TipoEsperaDTO tipo) {
		return tipo != null ? TipoEspera.valueOf(TipoEspera.class,
				tipo.toString()) : null;
	}
}
