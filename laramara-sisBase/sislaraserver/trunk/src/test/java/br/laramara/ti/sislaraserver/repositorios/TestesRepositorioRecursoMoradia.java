package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoMoradia;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioRecursoMoradia extends TestesIntegracaoAbstrato {

	public TestesRepositorioRecursoMoradia() {
		super("DadosTestesRepositorioRecursoMoradia.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_recurso_moradia_servico_pesquisa_todos() {
		RepositorioRecursoMoradia repositorio = Registro.obterRepositorioRecursoMoradia();

		List<RecursoMoradia> servicos = repositorio.obterTodos();

		Assert.assertEquals(servicos.size(), 2);
	}

}
