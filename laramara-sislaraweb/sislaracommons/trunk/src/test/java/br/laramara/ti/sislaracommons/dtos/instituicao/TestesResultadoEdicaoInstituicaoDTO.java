package br.laramara.ti.sislaracommons.dtos.instituicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoEdicaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoInstituicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoinstituicaodto_sem_erro_foi_construido() {
		ResultadoEdicaoInstituicaoDTO resultadoEdicaoInstituicaoDto = new ResultadoEdicaoInstituicaoDTO();
		resultadoEdicaoInstituicaoDto.efetuadoComSucesso(new InstituicaoDTO());

		Assert.assertTrue(resultadoEdicaoInstituicaoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoInstituicaoDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoInstituicaoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoinstituicaodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoInstituicaoDTO resultadoEdicaoInstituicaoDto = new ResultadoEdicaoInstituicaoDTO();
		resultadoEdicaoInstituicaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoInstituicaoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoInstituicaoDto.obterMensagens());
	}
}
