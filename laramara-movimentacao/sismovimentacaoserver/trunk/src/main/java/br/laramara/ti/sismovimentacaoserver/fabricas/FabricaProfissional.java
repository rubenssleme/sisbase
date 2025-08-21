package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

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
