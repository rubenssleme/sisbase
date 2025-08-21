package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.EspecificacaoGeracaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioGrupo extends TestesIntegracaoAbstrato {

	private RepositorioGrupo repositorio;

	public TestesRepositorioGrupo() {
		super("DadosTestesRepositorioGrupo.xml");
		repositorio = Registro.obterRepositorioGrupo();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_salva_e_obtem_grupo_cadastrado_com_sucesso() {

		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDadosSemAtendimentos();

		Grupo grupoSalvo = repositorio.salvar(grupo);

		Grupo grupoObtido = repositorio.obterPorId(grupoSalvo.getId());

		Assert.assertEquals(grupoSalvo.getSetor(), grupoObtido.getSetor());
		Assert.assertEquals(grupoSalvo.getDescricaoTipoAtendimento().getId(), grupoObtido
				.getDescricaoTipoAtendimento().getId());
		Assert.assertEquals(grupoSalvo.getDataInicio(), grupoObtido.getDataInicio());
		Assert.assertEquals(grupoSalvo.getDataTermino(),
				grupoObtido.getDataTermino());
		Assert.assertEquals(grupoSalvo.getNomeGrupo(), grupoObtido.getNomeGrupo());
		Assert.assertEquals(grupoSalvo.getTurma(), grupoObtido.getTurma());
		Assert.assertEquals(grupoSalvo.getNivel(), grupoObtido.getNivel());
		Assert.assertEquals(grupoSalvo.getModulosPeriodos().size(), grupoObtido
				.getModulosPeriodos().size());
		Assert.assertEquals(grupoSalvo.getDescricao(), grupoObtido.getDescricao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_salva_atendimento_com_sucesso() {
		String data = "31/12/2012";
		String horaInicial = "09:00";
		String horaFinal = "12:00";
		String descricao = "texto de descricao";
		String interdisciplinar = "texto de interdisciplinar";

		Grupo grupo = ContextoGrupo
				.fabricarGrupoComTodosOsDadosSemAtendimentos();

		Grupo grupoSalvo = repositorio.salvar(grupo);
		EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento = new EspecificacaoGeracaoAtendimento(
				grupoSalvo.getModulosPeriodos().get(0).getId(), data, new Horario(horaInicial, horaFinal));
		grupoSalvo.getModulosPeriodos().get(0).gerarAtendimentos(grupoSalvo, especificacaoGeracaoAtendimento);
		Grupo grupoAposGeracaoAtendimento = repositorio.salvar(grupoSalvo);

		AtendimentoGrupo atendimento = grupoAposGeracaoAtendimento
				.getModulosPeriodos().get(0).getAtendimentosGrupo().get(0);
		atendimento.setDescricao(descricao);
		atendimento.setInterdisciplinar(interdisciplinar);

		Grupo grupoAposSalvarAtendimento = repositorio
				.salvar(grupoAposGeracaoAtendimento);

		AtendimentoGrupo atendimentoAposAlteracao = grupoAposSalvarAtendimento
				.getModulosPeriodos().get(0).getAtendimentosGrupo().get(0);

		Assert.assertEquals(atendimentoAposAlteracao.getData(), data);
		Assert.assertEquals(atendimentoAposAlteracao.getHorario().getHoraInicio(),
				horaInicial);
		Assert.assertEquals(atendimentoAposAlteracao.getHorario().getHoraTermino(), horaFinal);
		Assert.assertEquals(atendimentoAposAlteracao.getDescricao(), descricao);
		Assert.assertEquals(atendimentoAposAlteracao.getInterdisciplinar(),
				interdisciplinar);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_salva_e_altera_grupo_com_sucesso() {

		String novaDescricao = "NOVA DESCRICAO";

		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDadosSemAtendimentos();

		Grupo grupoSalvo = repositorio.salvar(grupo);

		Grupo grupoObtido = repositorio.obterPorId(grupoSalvo.getId());
		grupoObtido.setDescricao(novaDescricao);

		repositorio.salvar(grupoObtido);
		Grupo grupoAposAlteracao = repositorio.obterPorId(grupoObtido.getId());

		Assert.assertEquals(grupoAposAlteracao.getDescricao(), novaDescricao);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_obtem_inativos_por_nome() {

		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDadosSemAtendimentos();
		grupo.setAtivo(false);

		repositorio.salvar(grupo);

		List<Grupo> gruposObtidos = repositorio.obterInativos(grupo
				.getNomeGrupo().getNome()+"-"+grupo.getTurma());

		Assert.assertEquals(gruposObtidos.size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_inexistente() {

		Assert.assertNull(repositorio.obterPorId(new Long(1222222)));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_grupo_obtem_todos_ativos() {

		Grupo grupo = ContextoGrupo
				.fabricarGrupoComTodosOsDadosSemAtendimentos();
		repositorio.salvar(grupo);

		Grupo grupoInativo = ContextoGrupo
				.fabricarGrupoComTodosOsDadosSemAtendimentos();
		grupoInativo.setAtivo(false);
		repositorio.salvar(grupoInativo);

		List<Grupo> gruposObtidos = repositorio.obterAtivos();

		Assert.assertEquals(gruposObtidos.size(), 1);
	}
}
