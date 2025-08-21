package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoEspecialidadeDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoespecialidadedto_foi_construida_com_sucesso() {
		String tipoEspecialidade = "DV";

		TipoEspecialidadeDTO tipoEspecialidadeDto = new TipoEspecialidadeDTO(
				tipoEspecialidade);

		Assert.assertEquals(tipoEspecialidadeDto.toString(), tipoEspecialidade);
	}
}
