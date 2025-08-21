package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.PossuiNecessitaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.PossuiNecessita;

public class FabricaPossuiNecessita extends FabricaBase<PossuiNecessitaDTO, PossuiNecessita> {
	public final PossuiNecessitaDTO converterParaDTO(PossuiNecessita relacaoRecurso) {
		return relacaoRecurso != null ? new PossuiNecessitaDTO(relacaoRecurso.toString()) : null;
	}

	public final PossuiNecessita converterParaDominio(PossuiNecessitaDTO relacaoRecurso) {
		return relacaoRecurso != null ? PossuiNecessita.valueOf(relacaoRecurso.toString()) : null;
	}
}
