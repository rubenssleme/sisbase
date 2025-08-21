package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoCadastroNovaSenhaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacaocadastronovasenhadto_foi_construido_com_sucesso() {
		String chave = UUID.randomUUID().toString();
		String novaSenha = "1234";
		String confirmacaoSenha = "1234";

		SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDTO = new SolicitacaoCadastroNovaSenhaDTO(chave,
				novaSenha, confirmacaoSenha);

		Assert.assertFalse(solicitacaoCadastroNovaSenhaDTO.toString().isEmpty());
		Assert.assertEquals(chave, solicitacaoCadastroNovaSenhaDTO.getChave());
		Assert.assertEquals(novaSenha, solicitacaoCadastroNovaSenhaDTO.getNovaSenha());
		Assert.assertEquals(confirmacaoSenha, solicitacaoCadastroNovaSenhaDTO.getConfirmacaoSenha());
	}

}
