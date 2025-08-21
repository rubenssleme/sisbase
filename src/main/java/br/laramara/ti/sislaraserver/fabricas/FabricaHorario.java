package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;

public class FabricaHorario extends FabricaBase<HorarioDTO, Horario> {

	@Override
	public HorarioDTO converterParaDTO(Horario horario) {
		return horario != null ? new HorarioDTO(horario.getHoraInicio(),
				horario.getHoraTermino()) : null;
	}

	@Override
	public Horario converterParaDominio(HorarioDTO horarioDto) {
		return horarioDto != null ? new Horario(horarioDto.getHoraInicio(),
				horarioDto.getHoraTermino()) : null;
	}
}
