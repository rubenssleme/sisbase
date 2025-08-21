package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioSituacaoHabitacional extends TestesIntegracaoAbstrato {

	public TestesRepositorioSituacaoHabitacional() {
		super("DadosTestesRepositorioSituacaoHabitacional.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_situacao_habitacional_pesquisa_todos() {
		RepositorioSituacaoHabitacional repositorio = Registro.obterRepositorioSituacaoHabitacional();

		List<SituacaoHabitacional> situacaoHabitacional = repositorio.obterTodos();

		Assert.assertEquals(situacaoHabitacional.size(), 2);
	}
}
