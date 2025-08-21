package br.laramara.ti.sislaraserver.repositorios;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;
import br.laramara.ti.sislaraserver.fabricas.ContextoProjeto;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioProjeto extends TestesIntegracaoAbstrato {

	public TestesRepositorioProjeto() {
		super("DadosTestesRepositorioProjeto.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_projeto_pesquisa_todos_registros() {
		RepositorioProjeto repositorio = Registro.obterRepositorioProjeto();

		List<Projeto> projetoObtidas = repositorio.obterTodos();

		Assert.assertEquals(projetoObtidas.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_projeto_pesquisa_ativos() {

		RepositorioProjeto repositorio = Registro.obterRepositorioProjeto();

		List<Projeto> projetoObtidos = repositorio.obterAtivos();

		Assert.assertEquals(projetoObtidos.size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_projeto_salva_com_sucesso() {

		RepositorioProjeto repositorio = Registro.obterRepositorioProjeto();
		Projeto projetoASalvar = ContextoProjeto.fabricarProjetoComTodosOsDados();
		projetoASalvar.setId(null);
		projetoASalvar.setRecursosDisponiveis(Arrays.asList());

		repositorio.salvar(projetoASalvar);
		
		List<Projeto> projetoObtidos = repositorio.obterAtivos();

		Assert.assertEquals(projetoObtidos.size(), 2);
	}
}
