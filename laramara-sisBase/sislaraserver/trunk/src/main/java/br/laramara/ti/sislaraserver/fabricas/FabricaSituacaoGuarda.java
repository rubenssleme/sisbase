package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoGuardaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoGuarda;

public class FabricaSituacaoGuarda extends
		FabricaBase<SituacaoGuardaDTO, SituacaoGuarda> {

	public final SituacaoGuardaDTO converterParaDTO(
			SituacaoGuarda situacaoGuarda) {
		return new SituacaoGuardaDTO(situacaoGuarda.getId(),
				situacaoGuarda.getDescricao());
	}

	public final SituacaoGuarda converterParaDominio(
			SituacaoGuardaDTO situacaoGuardaDto) {
		return new SituacaoGuarda(situacaoGuardaDto.getId(),
				situacaoGuardaDto.toString());
	}
}
