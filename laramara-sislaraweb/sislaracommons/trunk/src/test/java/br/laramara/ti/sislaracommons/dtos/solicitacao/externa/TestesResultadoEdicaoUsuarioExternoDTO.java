package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoUsuarioExternoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaousuarioexternodto_foi_construido_com_sucesso() {
		String mensagem = "Edição de usuário externo realizada com sucesso.\n";

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDTO = new ResultadoEdicaoUsuarioExternoDTO();

		resultadoEdicaoUsuarioExternoDTO.efetuadoComSucesso();

		Assert.assertTrue(resultadoEdicaoUsuarioExternoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoUsuarioExternoDTO.getMensagem(), mensagem);
	}

}
