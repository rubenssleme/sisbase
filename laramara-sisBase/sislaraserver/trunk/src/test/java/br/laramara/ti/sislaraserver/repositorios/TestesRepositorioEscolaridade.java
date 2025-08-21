package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioEscolaridade extends TestesIntegracaoAbstrato{

	public TestesRepositorioEscolaridade() {
		super("DadosTestesRepositorioEscolaridade.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_escolaridade_pesquisa_todos_escolaridades() {
		RepositorioEscolaridade repositorio = Registro.obterRepositorioEscolaridade();

		List<Escolaridade> escolaridadesObtidas = repositorio.obterTodos();

		Assert.assertEquals(escolaridadesObtidas.size(), 2);
	}
}
