package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemanaEHorario;

public class ContextoDiasSemanaEHorarios {

	public static List<DiaSemanaEHorario> fabricarComTodosOsDados() {
		DiaSemanaEHorario diaSemanaEHorario = gerarDiaSemanaEHorario();
		List<DiaSemanaEHorario> diasSemanaEHorarios = new ArrayList<>();
		diasSemanaEHorarios.add(diaSemanaEHorario);
		return diasSemanaEHorarios;
	}

	public static DiaSemanaEHorario gerarDiaSemanaEHorario() {
		DiaSemanaEHorario diaSemanaEHorario = new DiaSemanaEHorario();
		diaSemanaEHorario.setDiaSemana(DiaSemana.QUINTA);
		diaSemanaEHorario.setHorario(new Horario("13:00", "15:00"));
		diaSemanaEHorario.setId(new Long(12222));
		return diaSemanaEHorario;
	}

	public static DiaSemanaEHorarioDTO construirDiaSemanaEHorarioDTO() {
		DiaSemanaEHorarioDTO diaSemanaEHorario = new DiaSemanaEHorarioDTO();
		diaSemanaEHorario.setId(new Long(12222));		
		diaSemanaEHorario.setDiaSemanaDto(new DiaSemanaDTO(DiaSemana.QUINTA.toString()));
		diaSemanaEHorario.setHorarioDto(new HorarioDTO("13:00", "15:00"));
		return diaSemanaEHorario;
	}

}
