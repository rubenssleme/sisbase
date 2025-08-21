package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDiaSemanaDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void diasemanadto_foi_construida_com_sucesso() {
		String diaSemana = "SEGUNDA";

		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO(diaSemana);

		Assert.assertEquals(diaSemanaDto.toString(), diaSemana);
	}
}
