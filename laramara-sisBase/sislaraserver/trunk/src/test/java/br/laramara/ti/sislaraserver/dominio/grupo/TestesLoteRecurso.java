package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoLoteRecurso;

public class TestesLoteRecurso {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void loterecurso_validacao_com_erro_obrigatoriedade() {
		LoteRecurso loteRecurso = new LoteRecurso();
		loteRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(loteRecurso.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void loteRecurso_validacao_sem_erro_obrigatoriedade() {
		LoteRecurso loteRecurso = ContextoLoteRecurso
				.fabricarComTodosOsDados();

		loteRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(loteRecurso.validado());
	}
}