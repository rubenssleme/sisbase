package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDiaSemanaEHorarioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void diasemanaehorariodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO("SEGUNDA");
		String horaInicio = "09:00";
		String horaTermino = "14:00";
		DiaSemanaEHorarioDTO diaSemanaEHorarioDTO = new DiaSemanaEHorarioDTO();
		diaSemanaEHorarioDTO.setId(id);
		diaSemanaEHorarioDTO.setDiaSemanaDto(diaSemanaDto);
		diaSemanaEHorarioDTO.setHorarioDto(new HorarioDTO(horaInicio, horaTermino));

		Assert.assertEquals(diaSemanaEHorarioDTO.getId(), id);
		Assert.assertEquals(diaSemanaEHorarioDTO.getDiaSemanaDto(), diaSemanaDto);
		Assert.assertEquals(diaSemanaEHorarioDTO.getHorarioDto().getHoraInicio(), horaInicio);
		Assert.assertEquals(diaSemanaEHorarioDTO.getHorarioDto().getHoraTermino(), horaTermino);
	}
}
