package br.laramara.ti.sislaracommons.dtos.evento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoDescricaoEventoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaodescricaoeventodto_foi_construido_com_sucesso() {
		ResultadoValidacaoDescricaoEventoDTO resultadoValidacaoDescricaoEventoDTO = new ResultadoValidacaoDescricaoEventoDTO();

		resultadoValidacaoDescricaoEventoDTO.efetuadoComSucesso(new DescricaoEventoDTO());

		Assert.assertFalse(resultadoValidacaoDescricaoEventoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoValidacaoDescricaoEventoDTO.sucesso());
	}
	
}
