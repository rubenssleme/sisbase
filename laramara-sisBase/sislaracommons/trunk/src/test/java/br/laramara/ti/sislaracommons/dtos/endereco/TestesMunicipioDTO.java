package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesMunicipioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void municipiodto_foi_construido_com_sucesso() {
		Long idMunicipioEsperado = new Long(4850);
		String stringMunicipioEsperado = "São Paulo";
		String stringfUfEspedado = "SP";

		MunicipioDTO municipioDto = new MunicipioDTO(idMunicipioEsperado,
				stringMunicipioEsperado, new UfDTO(stringfUfEspedado));

		Assert.assertEquals(idMunicipioEsperado, municipioDto.getId());
		Assert.assertEquals(stringMunicipioEsperado, municipioDto.toString());
		Assert.assertEquals(stringfUfEspedado, municipioDto.getUf().toString());
	}
}
