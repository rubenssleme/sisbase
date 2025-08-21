package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioContribuinte extends TestesIntegracaoAbstrato {

	private RepositorioContribuinte repositorio;

	public TestesRepositorioContribuinte() {
		super("DadosTestesRepositorioContribuinte.xml");
		repositorio = Registro.obterRepositorioContribuinte();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_contribuinte_pesquisa_todas() {
		List<Contribuinte> contribuintesObtidos = repositorio.obterTodos();

		Assert.assertEquals(contribuintesObtidos.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_contribuinte_pesquisa_por_nome() {
		List<Contribuinte> contribuintesObtidos = repositorio
				.obterPorNome("paulo");

		Assert.assertEquals(contribuintesObtidos.size(), 1);
	}
}
