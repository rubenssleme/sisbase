package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoModuloRelacaoBaseDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaomodulorelacaobasedto_sem_erro_foi_construido() {
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloRelacaoBaseDto = new ResultadoValidacaoModuloRelacaoBaseDTO();
		resultadoValidacaoModuloRelacaoBaseDto
				.efetuadoComSucesso(new ModuloUsuarioDTO());

		Assert.assertTrue(resultadoValidacaoModuloRelacaoBaseDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaomodulorelacaobasedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloRelacaoBaseDto = new ResultadoValidacaoModuloRelacaoBaseDTO();
		resultadoValidacaoModuloRelacaoBaseDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoModuloRelacaoBaseDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoModuloRelacaoBaseDto
				.obterMensagens());
	}
}
