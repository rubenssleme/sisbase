package br.laramara.ti.sislaraserver.dominio.solicitacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaSolicitacaoRelatorio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_solicitacao_relatorio_construido_com_sucesso() {
		String id = "123";
		String protocolo = "38823";

		EspecificacaoPesquisaSolicitacaoRelatorio especificacao = new EspecificacaoPesquisaSolicitacaoRelatorio();
		especificacao.setProntuario(id);
		especificacao.setProtocolo(protocolo);

		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(especificacao.getProntuario().toString(),
				id.toString());
		Assert.assertEquals(especificacao.getProtocolo().toString(),
				protocolo.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_solicitacao_relatorio_com_erro_obrigatoriedade() {
		EspecificacaoPesquisaSolicitacaoRelatorio especificacao = new EspecificacaoPesquisaSolicitacaoRelatorio();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}
