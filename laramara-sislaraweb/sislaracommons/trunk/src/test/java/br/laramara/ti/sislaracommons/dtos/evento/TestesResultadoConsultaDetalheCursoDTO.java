package br.laramara.ti.sislaracommons.dtos.evento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoConsultaDetalheCursoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoconsultadetalhecursodto_foi_construido_com_sucesso() {
		ResultadoConsultaDetalheCursoDTO resultadoConsultaDetalheCursoDTO = new ResultadoConsultaDetalheCursoDTO();

		resultadoConsultaDetalheCursoDTO.efetuadoComSucesso(new DetalheCursoDTO());

		Assert.assertFalse(resultadoConsultaDetalheCursoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoConsultaDetalheCursoDTO.sucesso());
		Assert.assertNotNull(resultadoConsultaDetalheCursoDTO.getDetalheCursoDto());
	}
}
