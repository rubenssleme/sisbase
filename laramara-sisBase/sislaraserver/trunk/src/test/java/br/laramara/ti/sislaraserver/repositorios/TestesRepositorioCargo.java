package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioCargo extends TestesIntegracaoAbstrato {

	public TestesRepositorioCargo() {
		super("DadosTestesRepositorioCargo.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_cargo_pesquisa_todos_registros() {
		RepositorioCargo repositorio = Registro.obterRepositorioCargo();

		List<Cargo> cargosObtidas = repositorio.obterTodos();

		Assert.assertEquals(cargosObtidas.size(), 2);
	}
}
