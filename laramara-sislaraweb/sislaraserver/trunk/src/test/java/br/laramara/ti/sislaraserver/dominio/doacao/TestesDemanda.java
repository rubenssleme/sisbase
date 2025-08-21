package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoDemanda;

public class TestesDemanda {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void demanda_validacao_com_erro_obrigatoriedade_e_validade() {
		Demanda demanda = new Demanda();
		demanda.setDataExpectativa("34/34/3443");
		demanda.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(demanda.validado());
		Assert.assertEquals(demanda.obterNumeroErros(), 2);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void demanda_validacao_sem_erro_obrigatoriedade() {
		Demanda demanda = ContextoDemanda.fabricarDemandaComTodosOsDados();
		demanda.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(demanda.validado());
	}
}
