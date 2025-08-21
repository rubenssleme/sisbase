package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemUsuariosBloqueadosDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemperfildto_sem_erro_foi_construido() {
		ResultadoListagemUsuariosBloqueadosDTO resultadoListagemUsuariosBloqueadosDto = new ResultadoListagemUsuariosBloqueadosDTO();
		resultadoListagemUsuariosBloqueadosDto
				.efetuadoComSucesso(new UsuarioContaAcessoBloqueadoDTO("1234",
						null));

		Assert.assertTrue(resultadoListagemUsuariosBloqueadosDto.sucesso());
		Assert.assertNotNull(resultadoListagemUsuariosBloqueadosDto
				.obterMensagens());
	}
}
