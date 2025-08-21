package br.laramara.ti.sislaracommons.dtos.evento;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDetalhesCursoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdetalhecursodto_foi_construido_com_sucesso() {
		ResultadoListagemDetalhesCursoDTO resultadoListagemDetalhesCursoDTO = new ResultadoListagemDetalhesCursoDTO();

		resultadoListagemDetalhesCursoDTO.efetuadoComSucesso(Arrays.asList(new DetalheCursoDTO()));

		Assert.assertFalse(resultadoListagemDetalhesCursoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoListagemDetalhesCursoDTO.sucesso());
		Assert.assertEquals(resultadoListagemDetalhesCursoDTO.getObjetosDto().size(), 1);
	}
}
