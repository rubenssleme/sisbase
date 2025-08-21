package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoSolicitacaoCadastroNovaSenhaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoalteracaosenhadto_sem_erro_foi_construido() {
		ResultadoSolicitacaoCadastroNovaSenhaDTO resultadoSolicitacaoCadastroNovaSenhaDTO = new ResultadoSolicitacaoCadastroNovaSenhaDTO();

		resultadoSolicitacaoCadastroNovaSenhaDTO.efetuadoComSucesso();

		Assert.assertTrue(resultadoSolicitacaoCadastroNovaSenhaDTO.sucesso());
		Assert.assertFalse(resultadoSolicitacaoCadastroNovaSenhaDTO.obterMensagens().isEmpty());
	}
}
