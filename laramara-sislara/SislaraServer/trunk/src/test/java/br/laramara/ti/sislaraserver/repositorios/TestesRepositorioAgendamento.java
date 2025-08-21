package br.laramara.ti.sislaraserver.repositorios;

import java.util.Calendar;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.StatusAgendamento;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioAgendamento extends TestesIntegracaoAbstrato {

	public TestesRepositorioAgendamento() {
		super("DadosTestesRepositorioAgendamento.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_agendamento_pesquisa_por_profissional_e_data() {
		Profissional profissional = new Profissional(new Long(1000), "Paulo",
				"1234");
		Calendar data = DataHoraUtils.criar(31, 12, 2012);

		RepositorioAgendamento repositorio = Registro
				.obterRepositorioAgendamento();
		List<Agendamento> agendamentosObtidos = repositorio.obterTodosPor(
				profissional, data);

		Assert.assertEquals(agendamentosObtidos.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_agendamento_pesquisa_com_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		EspecificacaoPesquisaAgendamento especificacao = ContextoEspecificacaoPesquisaAgendamento
				.fabricarDominioComTodosOsDados();
		especificacao.setProfissional(new Profissional(new Long(1000), "Josep", "1234"));
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setDataInicio("01/01/2012");
		especificacao.setDataTermino("31/12/2012");
		especificacao.setStatusAgendamento(StatusAgendamento.AGENDADO);
		especificacao.setProntuario("12222");

		RepositorioAgendamento repositorio = Registro
				.obterRepositorioAgendamento();

		List<Agendamento> agendamentosObtidos = repositorio
				.obterPor(especificacao);

		Assert.assertEquals(agendamentosObtidos.size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_agendamento_pesquisa_com_especificacao_usando_apenas_data_inicio() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		EspecificacaoPesquisaAgendamento especificacao = ContextoEspecificacaoPesquisaAgendamento
				.fabricarDominioComTodosOsDados();
		especificacao.setProfissional(new Profissional(new Long(1000), "Josep", "1234"));
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setDataInicio("30/12/2012");

		RepositorioAgendamento repositorio = Registro
				.obterRepositorioAgendamento();

		List<Agendamento> agendamentosObtidos = repositorio
				.obterPor(especificacao);

		Assert.assertEquals(agendamentosObtidos.size(), 2);
	}
}
