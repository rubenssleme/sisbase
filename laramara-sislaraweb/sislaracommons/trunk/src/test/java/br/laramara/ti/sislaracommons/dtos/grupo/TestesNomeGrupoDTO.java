package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesNomeGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nomegrupodto_foi_construida_com_sucesso() {
		Long id = new Long(1000);
		String nomeGrupo = "G0";

		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(id, nomeGrupo);

		Assert.assertEquals(nomeGrupoDto.getId(), id);
		Assert.assertEquals(nomeGrupoDto.toString(), nomeGrupo);
	}
}
