package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ProfissionalVinculo;

public class FabricaProfissionalVinculo extends
		FabricaBase<ProfissionalVinculoDTO, ProfissionalVinculo> {

	@Override
	public ProfissionalVinculo converterParaDominio(
			ProfissionalVinculoDTO profissionalVinculoDto) {
		ProfissionalVinculo profissionalVinculo = null;
		if (profissionalVinculoDto != null) {
			profissionalVinculo = new ProfissionalVinculo();
			if (profissionalVinculoDto.getId() != null) {
				profissionalVinculo.setId(profissionalVinculoDto.getId());
			}
			profissionalVinculo.setProfissional(new FabricaProfissional()
					.converterParaDominio(profissionalVinculoDto
							.getProfissionalDto()));
			profissionalVinculo.setParticipante(profissionalVinculoDto
					.isParticipante());
		}
		return profissionalVinculo;
	}

	@Override
	public ProfissionalVinculoDTO converterParaDTO(
			ProfissionalVinculo profissionalVinculo) {
		ProfissionalVinculoDTO profissionalVinculoDto = null;
		if (profissionalVinculo != null) {
			profissionalVinculoDto = new ProfissionalVinculoDTO();
			profissionalVinculoDto.setId(profissionalVinculo.getId());
			profissionalVinculoDto.setProfissionalDto(new FabricaProfissional()
					.converterParaDTO(profissionalVinculo.getProfissional()));
			profissionalVinculoDto.setParticipante(profissionalVinculo
					.isParticipante());
		}
		return profissionalVinculoDto;
	}
}
