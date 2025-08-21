package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.doacao.Filial;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioFilial extends TestesIntegracaoAbstrato{
	
	public TestesRepositorioFilial() {
		super("DadosTestesRepositorioFilial.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_filial_pesquisa_todos_registros() {
		RepositorioFilial repositorio = Registro.obterRepositorioFilial();

		List<Filial> filialObtidas = repositorio.obterTodos();

		Assert.assertEquals(filialObtidas.size(), 2);
	}
}
