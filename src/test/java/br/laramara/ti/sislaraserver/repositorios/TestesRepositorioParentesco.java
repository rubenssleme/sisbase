package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioParentesco extends TestesIntegracaoAbstrato {

	public TestesRepositorioParentesco() {
		super("DadosTestesRepositorioParentesco.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_parentesco_obtem_todos_parentescos() {
		RepositorioParentesco repositorio = Registro
				.obterRepositorioParentesco();

		List<Parentesco> parentescosObtidas = repositorio.obterTodos();

		Assert.assertEquals(parentescosObtidas.size(), 4);
	}
}
