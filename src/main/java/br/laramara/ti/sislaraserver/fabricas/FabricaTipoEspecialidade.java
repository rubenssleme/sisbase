package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.TipoEspecialidadeDTO;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;

public class FabricaTipoEspecialidade extends FabricaBase<TipoEspecialidadeDTO, TipoEspecialidade> {
	public final TipoEspecialidadeDTO converterParaDTO(TipoEspecialidade tipoEspecialidade) {
		return tipoEspecialidade != null ? new TipoEspecialidadeDTO(tipoEspecialidade.toString()) : null;
	}

	public final TipoEspecialidade converterParaDominio(TipoEspecialidadeDTO tipoEspecialidade) {
		return tipoEspecialidade != null ? TipoEspecialidade.valueOf(TipoEspecialidade.class,
				tipoEspecialidade.toString()) : null;
	}
}
