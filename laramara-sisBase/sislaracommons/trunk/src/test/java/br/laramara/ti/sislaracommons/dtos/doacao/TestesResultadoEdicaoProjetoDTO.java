package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoProjetoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoprojetodto_sem_erro_foi_construido() {
		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDto = new ResultadoEdicaoProjetoDTO();
		resultadoEdicaoProjetoDto.efetuadoComSucesso(new ProjetoDTO());

		Assert.assertTrue(resultadoEdicaoProjetoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoProjetoDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoProjetoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoprojetodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDto = new ResultadoEdicaoProjetoDTO();
		resultadoEdicaoProjetoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoProjetoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoProjetoDto.obterMensagens());
	}

}
