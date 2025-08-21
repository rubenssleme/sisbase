package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioPais extends TestesIntegracaoAbstrato {

	public TestesRepositorioPais() {
		super("DadosTestesRepositorioPais.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_pais_pesquisa_todos_paises() {
		RepositorioPais repositorio = Registro.obterRepositorioPais();

		List<Pais> paisesObtidas = repositorio.obterTodos();

		Assert.assertEquals(paisesObtidas.size(), 2);
	}
}
