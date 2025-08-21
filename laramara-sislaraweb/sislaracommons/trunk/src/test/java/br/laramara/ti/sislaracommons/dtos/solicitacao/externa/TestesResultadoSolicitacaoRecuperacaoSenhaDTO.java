package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoSolicitacaoRecuperacaoSenhaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadosolicitacaorecuperacaosenhadto_foi_construido_com_sucesso() {
		String mensagem = "Link de alteração de senha enviado para o email com sucesso.";
		ResultadoSolicitacaoRecuperacaoSenhaDTO resultadoSolicitacaoRecuperacaoSenhaDTO = new ResultadoSolicitacaoRecuperacaoSenhaDTO();

		resultadoSolicitacaoRecuperacaoSenhaDTO.efetuadoComSucesso();

		Assert.assertTrue(resultadoSolicitacaoRecuperacaoSenhaDTO.sucesso());
		Assert.assertTrue(resultadoSolicitacaoRecuperacaoSenhaDTO.getMensagem().contains(mensagem));
	}
}
