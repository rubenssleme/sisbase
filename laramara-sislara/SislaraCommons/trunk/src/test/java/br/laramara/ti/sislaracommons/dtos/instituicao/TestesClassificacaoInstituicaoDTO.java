package br.laramara.ti.sislaracommons.dtos.instituicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesClassificacaoInstituicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void classificacaoinstituicao_dto_foi_construido_com_sucesso() {
		String stringClassificacaoInstituicaoEsperado = "FEDERAL";

		ClassificacaoInstituicaoDTO classificacaoInstituicaoDto = new ClassificacaoInstituicaoDTO(
				stringClassificacaoInstituicaoEsperado);

		Assert.assertEquals(stringClassificacaoInstituicaoEsperado,
				classificacaoInstituicaoDto.toString());
	}
}
