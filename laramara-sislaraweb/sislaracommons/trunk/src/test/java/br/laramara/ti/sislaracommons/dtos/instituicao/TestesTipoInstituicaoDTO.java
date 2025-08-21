package br.laramara.ti.sislaracommons.dtos.instituicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoInstituicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoinstituicaodto_foi_construido_com_sucesso() {
		String stringTipoInstituicaoEsperado = "FACULDADE";

		TipoInstituicaoDTO tipoInstituicaoDto = new TipoInstituicaoDTO(stringTipoInstituicaoEsperado);

		Assert.assertEquals(stringTipoInstituicaoEsperado, tipoInstituicaoDto.toString());
	}
}
