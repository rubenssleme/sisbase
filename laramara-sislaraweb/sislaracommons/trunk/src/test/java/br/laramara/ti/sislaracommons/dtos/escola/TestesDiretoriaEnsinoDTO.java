package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDiretoriaEnsinoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void diretoriaensinodto_foi_construida_com_sucesso() {
		Long id = new Long(1234);
		String diretoriaEnsino = "Diretoria de Ensino do Centro";

		DiretoriaEnsinoDTO diretoriaEnsinoDto = new DiretoriaEnsinoDTO(id,
				diretoriaEnsino);

		Assert.assertEquals(diretoriaEnsinoDto.getId(), id);
		Assert.assertEquals(diretoriaEnsinoDto.toString(), diretoriaEnsino);
	}
}
