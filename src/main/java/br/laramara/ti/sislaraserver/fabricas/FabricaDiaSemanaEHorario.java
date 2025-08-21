package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemanaEHorario;

public class FabricaDiaSemanaEHorario extends FabricaBase<DiaSemanaEHorarioDTO, DiaSemanaEHorario> {

	@Override
	public DiaSemanaEHorario converterParaDominio(DiaSemanaEHorarioDTO diaSemanaEHorarioDto) {
		DiaSemanaEHorario diaSemanaEHorario = new DiaSemanaEHorario();
		if (diaSemanaEHorarioDto.getId() != null) {
			diaSemanaEHorario.setId(new Long(diaSemanaEHorarioDto.getId()));
		}
		diaSemanaEHorario
				.setDiaSemana(new FabricaDiaSemana().converterParaDominio(diaSemanaEHorarioDto.getDiaSemanaDto()));
		diaSemanaEHorario.setHorario(new FabricaHorario().converterParaDominio(diaSemanaEHorarioDto.getHorarioDto()));
		return diaSemanaEHorario;
	}

	@Override
	public DiaSemanaEHorarioDTO converterParaDTO(DiaSemanaEHorario diaSemanaEHorario) {
		DiaSemanaEHorarioDTO diaSemanaEHorarioDto = null;
		if (diaSemanaEHorario != null) {
			diaSemanaEHorarioDto = new DiaSemanaEHorarioDTO();
			diaSemanaEHorarioDto.setId(diaSemanaEHorario.getId());
			diaSemanaEHorarioDto
					.setDiaSemanaDto(new FabricaDiaSemana().converterParaDTO(diaSemanaEHorario.getDiaSemana()));
			diaSemanaEHorarioDto.setHorarioDto(new FabricaHorario().converterParaDTO(diaSemanaEHorario.getHorario()));
		}
		return diaSemanaEHorarioDto;
	}

}
