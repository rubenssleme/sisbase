package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesTurnoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void turno_dto_foi_construida_com_sucesso() {
		String descricao = "MANHA";

		TurnoDTO turnoDto = new TurnoDTO();
		turnoDto.setTurno(descricao);

		Assert.assertEquals(turnoDto.toString(), descricao);
	}
}
