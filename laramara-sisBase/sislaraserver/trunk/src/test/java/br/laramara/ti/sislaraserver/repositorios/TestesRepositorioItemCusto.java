package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioItemCusto extends TestesIntegracaoAbstrato {

	public TestesRepositorioItemCusto() {
		super("DadosTestesRepositorioItemCusto.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_item_custo_pesquisa_todos_registros_de_doenca() {
		RepositorioItemCusto repositorio = Registro.obterRepositorioItemCusto();

		List<ItemCusto> cargosObtidas = repositorio.obterTodosDaDoenca();

		Assert.assertEquals(cargosObtidas.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_item_custo_pesquisa_todos_registros_de_deficiencia() {
		RepositorioItemCusto repositorio = Registro.obterRepositorioItemCusto();

		List<ItemCusto> cargosObtidas = repositorio.obterTodosDaDeficiencia();

		Assert.assertEquals(cargosObtidas.size(), 2);
	}
}
