package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecurso;

public class TestesEspecificacaoPesquisaDemanda {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_demanda_construido_com_sucesso() {
		String prontuario = "1234";
		String rg = "433221A";
		Recurso recurso = ContextoRecurso.fabricarRecursoComTodosOsDados();
		StatusDemanda statusDemanda = StatusDemanda.AGUARDANDO;

		EspecificacaoPesquisaDemanda especificacao = new EspecificacaoPesquisaDemanda();
		especificacao.setRecurso(recurso);
		especificacao.setProntuario(prontuario);
		especificacao.setRg(rg);
		especificacao.setStatusDemanda(statusDemanda);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(especificacao.getRecurso().getId(), recurso.getId());
		Assert.assertEquals(especificacao.getProntuario(), new Long(prontuario));
		Assert.assertEquals(especificacao.getRg(), rg);
		Assert.assertEquals(especificacao.getStatusDemanda(), statusDemanda);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_demanda_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoPesquisaDemanda especificacao = new EspecificacaoPesquisaDemanda();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}
