package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaPerfilPreenchidoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoConsultaPerfilPreenchidoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoconsultaperfilpreenchidodto_foi_construido_com_sucesso() {
		boolean perfilPreenchido = true;
		
		ResultadoConsultaPerfilPreenchidoDTO resultadoConsultaPerfilPreenchidoDTO = new ResultadoConsultaPerfilPreenchidoDTO();

		resultadoConsultaPerfilPreenchidoDTO.efetuadoComSucesso(perfilPreenchido);

		Assert.assertTrue(resultadoConsultaPerfilPreenchidoDTO.sucesso());
		Assert.assertEquals(resultadoConsultaPerfilPreenchidoDTO.isPerfilPreenchido(), perfilPreenchido);
	}

}
