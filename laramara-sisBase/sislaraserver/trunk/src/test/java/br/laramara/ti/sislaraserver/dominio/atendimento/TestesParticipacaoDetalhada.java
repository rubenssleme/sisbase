package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesParticipacaoDetalhada {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void participacao_detalhada_validacao_com_erro_obrigatoriedade_e_tamanho_maximo() {
		ParticipacaoDetalhada participacaoDetalhada = new ParticipacaoDetalhada();

		participacaoDetalhada.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(participacaoDetalhada.validado());
		Assert.assertEquals(participacaoDetalhada.obterNumeroErros(), 2);
	}
}
