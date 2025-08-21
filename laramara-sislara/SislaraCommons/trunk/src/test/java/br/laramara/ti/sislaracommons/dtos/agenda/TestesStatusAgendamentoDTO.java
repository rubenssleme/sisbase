package br.laramara.ti.sislaracommons.dtos.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusAgendamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusagendamentodto_foi_construido_com_sucesso() {
		String string = "AGENDADO";

		StatusAgendamentoDTO agendamentoDto = new StatusAgendamentoDTO(string);

		Assert.assertEquals(string, agendamentoDto.toString());
	}
}
