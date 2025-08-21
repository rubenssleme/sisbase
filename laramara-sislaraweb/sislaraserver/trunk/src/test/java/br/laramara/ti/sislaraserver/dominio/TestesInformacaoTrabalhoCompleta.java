package br.laramara.ti.sislaraserver.dominio;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoTrabalhoCompleta;

public class TestesInformacaoTrabalhoCompleta {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalhocompleta_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = ContextoInformacaoTrabalhoCompleta
				.fabricarInformacaoTrabalhoCompletaComTodosOsDados();
		informacaoTrabalhoCompleta
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(informacaoTrabalhoCompleta.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalhocompleta_validacao_com_erro_de_validade_e_obrigatoriedade_de_dados() {
		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = new InformacaoTrabalhoCompleta();
		informacaoTrabalhoCompleta.setDataInicial(null);
		informacaoTrabalhoCompleta.setDataFinal("2323");
		informacaoTrabalhoCompleta
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(informacaoTrabalhoCompleta.validado());
		Assert.assertEquals(informacaoTrabalhoCompleta.obterNumeroErros(), 5,
				"Deveria haver 5 infrações de obrigatoriedade.");
	}
}
