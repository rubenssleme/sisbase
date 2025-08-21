package br.laramara.ti.sislaraserver.fabricas.externa;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.externa.SolicitacaoCadastroNovaSenha;
import br.laramara.ti.sislaraserver.fabricas.FabricaSolicitacaoCadastroNovaSenha;

public class TestesFabricaSolicitacaoAlteracaoSenha {

	@Test
	public void fabrica_solicitacaoalteracaosenha_converte_para_dominio() {
		String chave = UUID.randomUUID().toString();
		String hashSenha1234 = "1234";

		SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDTO = new SolicitacaoCadastroNovaSenhaDTO(chave, "1234",
				"1234");

		SolicitacaoCadastroNovaSenha solicitacaoAlteracaoSenha = new FabricaSolicitacaoCadastroNovaSenha()
				.converterParaDominio(solicitacaoCadastroNovaSenhaDTO);

		Assert.assertEquals(solicitacaoCadastroNovaSenhaDTO.getChave(), solicitacaoAlteracaoSenha.getChave());
		Assert.assertEquals(hashSenha1234, solicitacaoAlteracaoSenha.getNovaSenha());
		Assert.assertEquals(hashSenha1234, solicitacaoAlteracaoSenha.getConfirmacaoSenha());
	}

}
