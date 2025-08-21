package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.trabalho.LocalTrabalhoDTO;
import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;

public class FabricaLocalTrabalho extends
		FabricaBase<LocalTrabalhoDTO, LocalTrabalho> {
	public final LocalTrabalhoDTO converterParaDTO(LocalTrabalho localTrabalho) {
		return localTrabalho != null ? new LocalTrabalhoDTO(
				localTrabalho.getId(), localTrabalho.getNome()) : null;
	}

	public final LocalTrabalho converterParaDominio(
			LocalTrabalhoDTO localTrabalhoDto) {
		return localTrabalhoDto != null ? new LocalTrabalho(
				localTrabalhoDto.getId(), localTrabalhoDto.toString()) : null;
	}
}
