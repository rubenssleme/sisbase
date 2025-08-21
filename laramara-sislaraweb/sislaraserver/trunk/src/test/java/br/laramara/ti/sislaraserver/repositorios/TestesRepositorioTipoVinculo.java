package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioTipoVinculo extends TestesIntegracaoAbstrato {

	public TestesRepositorioTipoVinculo() {
		super("DadosTestesRepositorioTipoVinculo.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_pesquisa_todos() {
		RepositorioTipoVinculo repositorio = Registro
				.obterRepositorioTipoVinculo();

		List<TipoVinculo> recursoObtidas = repositorio.obterTodos();

		Assert.assertEquals(recursoObtidas.size(), 2);
	}
}
