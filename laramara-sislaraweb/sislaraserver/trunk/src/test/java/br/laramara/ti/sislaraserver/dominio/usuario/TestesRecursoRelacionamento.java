package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecursoRelacionamento;

public class TestesRecursoRelacionamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_relacionamento_validacao_sem_erro() {
		RecursoRelacionamento recursoRelacionamento = ContextoRecursoRelacionamento.construirRecursoRelacionamento();
		recursoRelacionamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(recursoRelacionamento.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_relacionamento_validacao_com_erro_obrigatoriedade() {
		RecursoRelacionamento recursoRelacionamento = new RecursoRelacionamento();
		recursoRelacionamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(recursoRelacionamento.validado());
		Assert.assertTrue(recursoRelacionamento.obterDescricaoErros().contains("Insira um Recurso."));
		Assert.assertTrue(recursoRelacionamento.obterDescricaoErros().contains("Insira uma opção possui/necessita para o recurso."));
	}
}
