package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periododto_foi_construida_com_sucesso() {
		String periodo = "MANHA";

		PeriodoDTO periodoDto = new PeriodoDTO(periodo);

		Assert.assertEquals(periodoDto.toString(), periodo);
	}
}
