package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoAtendimento;

public class TestesInformacaoAtendimento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoatendimento_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		InformacaoAtendimento informacaoAtendimento = ContextoInformacaoAtendimento
				.fabricarInformacaoTrabalhoComTodosOsDados();
		informacaoAtendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(informacaoAtendimento.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoatendimento_com_erro_de_obrigatoriedade_de_dados() {
		InformacaoAtendimento informacaoAtendimento = new InformacaoAtendimento();
		informacaoAtendimento.setFrequencia(null);
		informacaoAtendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(informacaoAtendimento.validado());
		Assert.assertNotNull(informacaoAtendimento.obterDescricaoErros());
		Assert.assertEquals(informacaoAtendimento.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}
}
