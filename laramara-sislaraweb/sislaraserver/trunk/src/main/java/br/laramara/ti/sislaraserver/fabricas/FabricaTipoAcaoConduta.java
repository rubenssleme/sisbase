package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoAcaoCondutaDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoAcaoConduta;

public class FabricaTipoAcaoConduta extends FabricaBase<TipoAcaoCondutaDTO, TipoAcaoConduta> {
	public final TipoAcaoCondutaDTO converterParaDTO(TipoAcaoConduta tipoAcaoConduta) {
		return tipoAcaoConduta != null ? new TipoAcaoCondutaDTO(tipoAcaoConduta.toString()) : null;
	}

	public final TipoAcaoConduta converterParaDominio(TipoAcaoCondutaDTO tipoAcaoConduta) {
		return tipoAcaoConduta != null ? TipoAcaoConduta.valueOf(TipoAcaoConduta.class, tipoAcaoConduta.toString())
				: null;
	}
}
