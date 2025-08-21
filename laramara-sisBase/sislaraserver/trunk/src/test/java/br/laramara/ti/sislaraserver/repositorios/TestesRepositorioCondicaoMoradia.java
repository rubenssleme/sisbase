package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioCondicaoMoradia extends TestesIntegracaoAbstrato {

	public TestesRepositorioCondicaoMoradia() {
		super("DadosTestesRepositorioCondicaoMoradia.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_condicao_moradia_pesquisa_todos() {
		RepositorioCondicaoMoradia repositorio = Registro.obterRepositorioCondicaoMoradia();

		List<CondicaoMoradia> condicaoMoradia = repositorio.obterTodos();

		Assert.assertEquals(condicaoMoradia.size(), 2);
	}

}
