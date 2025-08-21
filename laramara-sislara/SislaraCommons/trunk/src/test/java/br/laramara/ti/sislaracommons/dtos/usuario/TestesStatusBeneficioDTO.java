package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusBeneficioDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void status_beneficio_dto_foi_construido_com_sucesso() {
		String stringStatusBeneficio = "SIM";

		StatusBeneficioDTO generoDto = new StatusBeneficioDTO(
				stringStatusBeneficio);

		Assert.assertEquals(stringStatusBeneficio, generoDto.toString());
	}
}
