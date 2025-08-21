package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fabricas.ContextoModulo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioAtendimentoIndividual extends
		TestesIntegracaoAbstrato {

	private RepositorioAtendimentoIndividual repositorio;

	public TestesRepositorioAtendimentoIndividual() {
		super("DadosTestesRepositorioAtendimentoIndividual.xml");
		Registro.inicializarContexto();
		repositorio = Registro.obterRepositorioAtendimentoIndividual();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_atendimento_individual_salva_e_obtem_atendimento_cadastrado_com_sucesso() {

		AtendimentoIndividual atendimento = ContextoAtendimentoIndividual
				.fabricarComTodosOsDados();

		atendimento.setHorario(new Horario("15:00", "17:30"));

		AtendimentoIndividual atendimentoSalvo = repositorio
				.salvar(atendimento);

		Assert.assertNotNull(atendimentoSalvo.getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_atendimento_individual_editar_atendimento_com_sucesso() {
		Setor setorAposAlteracao = Setor.PROCEJA;
		AtendimentoIndividual atendimento = ContextoAtendimentoIndividual
				.fabricarComTodosOsDados();

		atendimento.setHorario(new Horario("15:00", "17:30"));

		AtendimentoIndividual atendimentoSalvo = repositorio
				.salvar(atendimento);
		atendimentoSalvo.setSetor(setorAposAlteracao);

		AtendimentoIndividual atendimentoIndividualAposAlteracao = repositorio
				.salvar(atendimentoSalvo);

		Assert.assertEquals(atendimentoIndividualAposAlteracao.getSetor(),
				setorAposAlteracao);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_atendimento_individual_pesquisa_com_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();
		EspecificacaoPesquisaAtendimentoIndividual especificacao = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacao.setProfissional(new Profissional(new Long(1000), "Josep",
				"1234"));
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setModulo(modulo);
		especificacao.setDataInicio("01/01/2012");
		especificacao.setDataTermino("31/12/2012");

		RepositorioAtendimentoIndividual repositorio = Registro
				.obterRepositorioAtendimentoIndividual();

		List<AtendimentoIndividual> agendamentosObtidos = repositorio
				.obterPor(especificacao);

		Assert.assertEquals(agendamentosObtidos.size(), 1);
	}
}
