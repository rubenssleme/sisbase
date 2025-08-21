package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioHabitacao extends TestesIntegracaoAbstrato {

	public TestesRepositorioHabitacao() {
		super("DadosTestesRepositorioHabitacao.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_habitacao_pesquisa_todos() {
		RepositorioHabitacao repositorio = Registro.obterRepositorioHabitacao();

		List<Habitacao> habitacao = repositorio.obterTodos();

		Assert.assertEquals(habitacao.size(), 2);
	}
}
