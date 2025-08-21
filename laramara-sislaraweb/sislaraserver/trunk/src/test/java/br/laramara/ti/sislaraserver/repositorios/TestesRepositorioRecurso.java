package br.laramara.ti.sislaraserver.repositorios;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.utilitarios.Registro;


public class TestesRepositorioRecurso extends
TestesIntegracaoAbstrato {

	public TestesRepositorioRecurso() {
		super("DadosTestesRepositorioRecurso.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_pesquisa_todos() {
		RepositorioRecurso repositorio = Registro.obterRepositorioRecurso();

		List<Recurso> recursoObtidas = repositorio.obterTodos();

		Assert.assertEquals(recursoObtidas.size(), 2);
	}

}
