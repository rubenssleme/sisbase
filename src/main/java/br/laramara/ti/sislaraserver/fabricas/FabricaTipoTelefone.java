package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;

public class FabricaTipoTelefone extends FabricaBase<TipoTelefoneDTO, TipoTelefone> {
	public final TipoTelefoneDTO converterParaDTO(
			TipoTelefone tipoTelefone) {
		return new TipoTelefoneDTO(tipoTelefone.toString());
	}

	public final TipoTelefone converterParaDominio(
			TipoTelefoneDTO tipoTelefone) {
		return TipoTelefone
				.valueOf(TipoTelefone.class, tipoTelefone.toString());
	}
}
