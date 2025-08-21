package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesHorarioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void horariodto_foi_construida_com_sucesso() {
		String horaInicio = "09:00";
		String horaTermino = "10:00";

		HorarioDTO horarioDto = new HorarioDTO(horaInicio, horaTermino);

		Assert.assertEquals(horarioDto.getHoraInicio(),
				horarioDto.getHoraInicio());
		Assert.assertEquals(horarioDto.getHoraTermino(),
				horarioDto.getHoraTermino());
	}
}
