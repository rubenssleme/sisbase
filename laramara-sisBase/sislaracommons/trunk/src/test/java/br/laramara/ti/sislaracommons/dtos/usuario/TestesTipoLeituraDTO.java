package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoLeituraDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoleituradto_foi_construido_com_sucesso() {
		String tipoLeitura = "AMPLIADO";

		TipoLeituraDTO statusDto = new TipoLeituraDTO(tipoLeitura);

		Assert.assertEquals(tipoLeitura, statusDto.toString());
	}
}
