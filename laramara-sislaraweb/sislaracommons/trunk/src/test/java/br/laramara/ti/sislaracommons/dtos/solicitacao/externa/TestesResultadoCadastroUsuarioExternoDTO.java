package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoCadastroUsuarioExternoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadocadastrousuarioexternodto_foi_construido_com_sucesso() {
		String mensagem = "Cadastro realizado com sucesso. Link de confirmação enviado para o seu e-mail.";
		ResultadoCadastroUsuarioExternoDTO resultadoCadastroUsuarioExternoDTO = new ResultadoCadastroUsuarioExternoDTO();

		resultadoCadastroUsuarioExternoDTO.efetuadoComSucesso();

		Assert.assertFalse(resultadoCadastroUsuarioExternoDTO.toString().isEmpty());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.sucesso());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.getMensagem().contains(mensagem));
	}
}
