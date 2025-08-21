package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public class FabricaProfissional extends FabricaBase<ProfissionalDTO, Profissional> {

	public final ProfissionalDTO converterParaDTO(
			Profissional profissional) {
		return profissional != null ? new ProfissionalDTO(profissional.getId(),
				profissional.getNome(), profissional.getChapa()) : null;
	}

	public final Profissional converterParaDominio(
			ProfissionalDTO profissionalDto) {
		return profissionalDto != null ? new Profissional(
				profissionalDto.getId(), profissionalDto.toString(),
				profissionalDto.getChapa()) : null;
	}
}
