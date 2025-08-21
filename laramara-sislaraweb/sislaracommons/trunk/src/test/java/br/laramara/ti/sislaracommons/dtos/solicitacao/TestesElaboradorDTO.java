package br.laramara.ti.sislaracommons.dtos.solicitacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesElaboradorDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void elaboradordto_foi_construido_com_sucesso() {
		String stringElaborador = "OFTALMOLOGIA";

		ElaboradorDTO elaboradorDto = new ElaboradorDTO(stringElaborador);

		Assert.assertEquals(stringElaborador, elaboradorDto.toString());
	}
}
