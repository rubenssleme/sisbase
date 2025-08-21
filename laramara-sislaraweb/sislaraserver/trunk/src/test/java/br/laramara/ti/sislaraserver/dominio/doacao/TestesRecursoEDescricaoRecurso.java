package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoRecurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecurso;

public class TestesRecursoEDescricaoRecurso {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_e_descricao_recurso_validacao_com_erro_obrigatoriedade_e_validade() {
		RecursoEDescricaoRecurso recursoEDescricaoRecurso = new RecursoEDescricaoRecurso(null, null);
		recursoEDescricaoRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(recursoEDescricaoRecurso.validado());
		Assert.assertEquals(recursoEDescricaoRecurso.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_e_descricao_recurso_validacao_sem_erro_obrigatoriedade() {
		RecursoEDescricaoRecurso recursoEDescricaoRecurso = new RecursoEDescricaoRecurso(
				ContextoRecurso.fabricarRecursoAlternativoComTodosOsDados(),
				ContextoDescricaoRecurso.construirDescricaoRecurso());
		recursoEDescricaoRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(recursoEDescricaoRecurso.validado());
	}
}
