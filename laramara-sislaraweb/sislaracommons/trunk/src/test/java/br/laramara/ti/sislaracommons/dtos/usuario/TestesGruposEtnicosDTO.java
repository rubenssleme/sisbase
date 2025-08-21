package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesGruposEtnicosDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupoetnicodto_foi_construido_com_sucesso() {
		String stringGrupoEtnicoEsperado = "BRANCO";

		GrupoEtnicoDTO grupoEtnicoDto = new GrupoEtnicoDTO(stringGrupoEtnicoEsperado);

		Assert.assertEquals(stringGrupoEtnicoEsperado, grupoEtnicoDto.toString());
	}
}
