package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;

public class TestesDiaSemanaEHorario {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dia_semana_e_horario_validacao_com_erro_obrigatoriedade() {
		DiaSemanaEHorario diaSemanaEHorario = new DiaSemanaEHorario();

		diaSemanaEHorario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(diaSemanaEHorario.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void dia_semana_e_horario_validacao_sem_erro_obrigatoriedade() {
		DiaSemanaEHorario diaSemanaEHorario = new DiaSemanaEHorario();
		diaSemanaEHorario.setDiaSemana(DiaSemana.DOMINGO);
		diaSemanaEHorario.setHorario(new Horario("09:00", "10:00"));

		diaSemanaEHorario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(diaSemanaEHorario.validado());
	}
}
