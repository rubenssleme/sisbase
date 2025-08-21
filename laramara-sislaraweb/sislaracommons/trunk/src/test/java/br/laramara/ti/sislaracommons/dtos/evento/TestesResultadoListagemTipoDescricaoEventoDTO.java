package br.laramara.ti.sislaracommons.dtos.evento;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoDescricaoEventoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipodescricaoeventodto_foi_construido_com_sucesso() {
		ResultadoListagemTipoDescricaoEventoDTO resultadoListagemTipoDescricaoEventoDTO = new ResultadoListagemTipoDescricaoEventoDTO();

		resultadoListagemTipoDescricaoEventoDTO.efetuadoComSucesso(Arrays.asList(new TipoDescricaoEventoDTO()));

		Assert.assertFalse(resultadoListagemTipoDescricaoEventoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoListagemTipoDescricaoEventoDTO.sucesso());
	}
}
