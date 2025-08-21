package br.laramara.ti.sislaraserver.dominio;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesHorario {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void horario_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		Horario horario = new Horario("08:00", "10:00");
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(horario.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void horario_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Horario horario = new Horario();
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(horario.validado());
		Assert.assertNotNull(horario.obterDescricaoErros());
		Assert.assertEquals(horario.obterNumeroErros(), 2,
				"Deveria haver 2 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void horario_validacao_com_erro_de_hora_anterior_maior_que_posterior() {
		Horario horario = new Horario("09:00", "08:00");
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(horario.validado());
		Assert.assertNotNull(horario.obterDescricaoErros());
		Assert.assertEquals(horario.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}
}
