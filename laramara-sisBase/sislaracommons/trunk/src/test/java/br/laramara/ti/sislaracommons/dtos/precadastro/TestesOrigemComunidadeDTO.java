package br.laramara.ti.sislaracommons.dtos.precadastro;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesOrigemComunidadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void origemcomunidadedto_foi_construida_com_sucesso() {
		String origem = "EMPRESA";

		PeriodoDTO origemComunidadeDto = new PeriodoDTO(origem);

		Assert.assertEquals(origemComunidadeDto.toString(), origem);
	}
}
