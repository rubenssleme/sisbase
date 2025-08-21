package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioExpectativa extends TestesIntegracaoAbstrato {

	public TestesRepositorioExpectativa() {
		super("DadosTestesRepositorioExpectativa.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_expectativa_pesquisa_todos() {
		RepositorioExpectativa repositorio = Registro.obterRepositorioExpectativa();

		List<Expectativa> servicos = repositorio.obterTodos();

		Assert.assertEquals(servicos.size(), 2);
	}
}
