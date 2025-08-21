package br.laramara.ti.sislaracommons.dtos.precadastro;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoPreCadastroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoprecadastrodto_sem_erro_foi_construido() {
		ResultadoEdicaoPreCadastroDTO resultadoEdicaoPreCadastroDto = new ResultadoEdicaoPreCadastroDTO();
		resultadoEdicaoPreCadastroDto.efetuadoComSucesso(new PreCadastroDTO());

		Assert.assertTrue(resultadoEdicaoPreCadastroDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoPreCadastroDto
				.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoPreCadastroDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaoprecadastrodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoPreCadastroDTO resultadoEdicaoPreCadastroDto = new ResultadoEdicaoPreCadastroDTO();
		resultadoEdicaoPreCadastroDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoPreCadastroDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoPreCadastroDto.obterMensagens());
	}
}
