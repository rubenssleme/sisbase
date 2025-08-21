package br.laramara.ti.sislaraserver.dominio.retirada;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.ContextoProfissional;

public class TestesRetirada {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void retirada_validacao_sem_erro_de_obrigatoriedade() {
		Retirada retirada = new Retirada();
		retirada.setProntuario(new Long(32838));
		retirada.setProfissional(ContextoProfissional.fabricarComTodosOsDados());
		retirada.setVoluntario(ContextoProfissional.fabricarComTodosOsDados());
		retirada.setContaAcessoResponsavelPorOperacaoRetirada(ContextoContaAcesso
				.fabricarComTodosOsDados());

		retirada.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(retirada.validado());
	}
}
