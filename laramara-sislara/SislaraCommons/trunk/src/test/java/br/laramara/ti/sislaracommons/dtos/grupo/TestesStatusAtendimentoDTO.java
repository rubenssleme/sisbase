package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusatendimentodto_foi_construida_com_sucesso() {
		String status = "NORMAL";

		StatusAtendimentoDTO statusDto = new StatusAtendimentoDTO(status);

		Assert.assertEquals(statusDto.toString(), status);
	}
}
