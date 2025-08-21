package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDreCefaiDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void drecefaidto_foi_construida_com_sucesso() {
		Long id = new Long(1234);
		String dreCefai = "CEFAI BUTANTAN";

		DreCefaiDTO dreCefaiDto = new DreCefaiDTO(id, dreCefai);

		Assert.assertEquals(dreCefaiDto.getId(), id);
		Assert.assertEquals(dreCefaiDto.toString(), dreCefai);
	}
}
