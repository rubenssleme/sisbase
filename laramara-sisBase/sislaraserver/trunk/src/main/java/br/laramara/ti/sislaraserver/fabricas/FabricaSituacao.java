package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaraserver.dominio.escola.SituacaoEducacional;

public class FabricaSituacao extends FabricaBase<SituacaoEducacionalDTO, SituacaoEducacional> {

	public final SituacaoEducacionalDTO converterParaDTO(SituacaoEducacional situacao) {
		return situacao != null ? new SituacaoEducacionalDTO(situacao.toString()) : null;
	}

	public final SituacaoEducacional converterParaDominio(SituacaoEducacionalDTO situacaoDto) {
		return situacaoDto != null ? SituacaoEducacional.valueOf(SituacaoEducacional.class,
				situacaoDto.toString()) : null;
	}
}
