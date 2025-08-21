package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioDreCefai extends TestesIntegracaoAbstrato{

	public TestesRepositorioDreCefai() {
		super("DadosTestesRepositorioDreCefai.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_dre_cefai_pesquisa_todos() {
		RepositorioDreCefai repositorio = Registro.obterRepositorioDreCefai();

		List<DreCefai> dreCefaiObtidos = repositorio.obterTodos();

		Assert.assertEquals(dreCefaiObtidos.size(), 2);
	}
}
