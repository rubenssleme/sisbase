package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoCadastroNovaSenhaDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadocadastronovasenhadto_foi_construido_com_sucesso() {
		String mensagem = "Cadastro de nova senha realizado com sucesso.";
		ResultadoSolicitacaoCadastroNovaSenhaDTO resultadoCadastroUsuarioExternoDTO = new ResultadoSolicitacaoCadastroNovaSenhaDTO();

		resultadoCadastroUsuarioExternoDTO.efetuadoComSucesso();

		Assert.assertFalse(resultadoCadastroUsuarioExternoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.sucesso());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.getMensagem().contains(mensagem));
	}
}
