package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPaisDAO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void paisdto_foi_construido_com_sucesso() {
		Long idPaisEsperado = new Long(1);
		String stringPaisEsperado = "Brasil";

		PaisDTO paisDto = new PaisDTO(idPaisEsperado, stringPaisEsperado);

		Assert.assertEquals(idPaisEsperado, paisDto.getId());
		Assert.assertEquals(stringPaisEsperado, paisDto.toString());
	}
}
