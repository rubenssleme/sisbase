package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioDoenca extends TestesIntegracaoAbstrato{
	
	public TestesRepositorioDoenca() {
		super("DadosTestesRepositorioDoenca.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_doenca_pesquisa_todos_registros() {
		RepositorioDoenca repositorio = Registro.obterRepositorioDoenca();

		List<Doenca> doencasObtidas = repositorio.obterTodos();

		Assert.assertEquals(doencasObtidas.size(), 2);
	}
}
