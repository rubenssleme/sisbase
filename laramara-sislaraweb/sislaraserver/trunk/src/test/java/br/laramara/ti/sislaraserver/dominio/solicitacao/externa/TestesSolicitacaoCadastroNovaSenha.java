package br.laramara.ti.sislaraserver.dominio.solicitacao.externa;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoCadastroNovaSenha {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void teste_solicitacaocadastronovasenha_valida_nova_senha_com_sucesso() {
		String chave = UUID.randomUUID().toString();
		String novaSenha = "1234";
		String confirmacaoSenha = "1234";
		
		SolicitacaoCadastroNovaSenha solicitacaoCadastroNovaSenha = new SolicitacaoCadastroNovaSenha(chave, novaSenha, confirmacaoSenha);
		
		Assert.assertFalse(solicitacaoCadastroNovaSenha.toString().isEmpty());
		Assert.assertTrue(solicitacaoCadastroNovaSenha.novaSenhaValida());
		Assert.assertEquals(solicitacaoCadastroNovaSenha.getChave(), chave);
		Assert.assertEquals(solicitacaoCadastroNovaSenha.getNovaSenha(), novaSenha);
		Assert.assertEquals(solicitacaoCadastroNovaSenha.getConfirmacaoSenha(), confirmacaoSenha);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void teste_solicitacaoalteracaosenha_valida_nova_senha_sem_sucesso() {
		String chave = UUID.randomUUID().toString();
		String novaSenha = "1234";
		String confirmacaoSenha = "4321";
		
		SolicitacaoCadastroNovaSenha solicitacaoCadastroNovaSenha = new SolicitacaoCadastroNovaSenha(chave, novaSenha, confirmacaoSenha);
		
		Assert.assertFalse(solicitacaoCadastroNovaSenha.novaSenhaValida());
	}
}
