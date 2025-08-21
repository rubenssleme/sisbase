package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioLocalTrabalho extends TestesIntegracaoAbstrato {

	public TestesRepositorioLocalTrabalho() {
		super("DadosTestesRepositorioLocalTrabalho.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_local_trabalho_pesquisa_todos_registros() {
		RepositorioLocalTrabalho repositorio = Registro.obterRepositorioLocalTrabalho();

		List<LocalTrabalho> localTrabalhoObtidas = repositorio.obterTodos();

		Assert.assertEquals(localTrabalhoObtidas.size(), 2);
	}

}
