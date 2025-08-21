package br.laramara.ti.sislaraserver.dominio.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;

public class TestesEspecificacaoGeracaoCopiaAgendamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_copia_agendamento_construido_com_sucesso() {
		String data = "31/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "12:00";

		EspecificacaoGeracaoCopiaAgendamento especificacao = new EspecificacaoGeracaoCopiaAgendamento();
		especificacao.setData(data);
		especificacao.setHorario(new Horario(horaInicio, horaTermino));

		Assert.assertEquals(especificacao.getData(), DataHoraUtils.criar(data));
		Assert.assertEquals(especificacao.getHorario().getHoraInicio(), horaInicio);
		Assert.assertEquals(especificacao.getHorario().getHoraTermino(), horaTermino);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_copia_agendamento_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoGeracaoCopiaAgendamento especificacao = new EspecificacaoGeracaoCopiaAgendamento();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_copia_agendamento_validacao_com_erro_de_faixa_de_data_e_horario_invalidos() {
		EspecificacaoGeracaoCopiaAgendamento especificacao = new EspecificacaoGeracaoCopiaAgendamento();
		especificacao.setData("31/12/2012");
		especificacao.setHorario(new Horario("12:00", "08:00"));
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}
