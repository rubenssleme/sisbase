package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoTelefoneDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipotelefonedto_foi_construida_com_sucesso() {
		String tipoTelefone = "CELULAR";
		TipoTelefoneDTO tipoTelefoneDto = new TipoTelefoneDTO(tipoTelefone);

		Assert.assertEquals(tipoTelefoneDto.toString(), tipoTelefone);
	}
}
