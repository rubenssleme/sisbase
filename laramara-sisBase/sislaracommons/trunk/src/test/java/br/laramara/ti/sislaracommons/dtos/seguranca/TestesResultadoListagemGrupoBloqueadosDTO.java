package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemGrupoBloqueadosDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemgruposbloqueadosdto_sem_erro_foi_construido() {
		ResultadoListagemGruposBloqueadosDTO resultadoListagemGruposBloqueadosDto = new ResultadoListagemGruposBloqueadosDTO();
		resultadoListagemGruposBloqueadosDto
				.efetuadoComSucesso(new GrupoContaAcessoBloqueadoDTO(
						"G02-01-31/12/1921", null));

		Assert.assertTrue(resultadoListagemGruposBloqueadosDto.sucesso());
		Assert.assertNotNull(resultadoListagemGruposBloqueadosDto
				.obterMensagens());
	}
}
