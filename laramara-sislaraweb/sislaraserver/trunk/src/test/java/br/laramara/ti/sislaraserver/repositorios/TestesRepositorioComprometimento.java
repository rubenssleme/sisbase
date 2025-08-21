package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioComprometimento extends TestesIntegracaoAbstrato {

	public TestesRepositorioComprometimento() {
		super("DadosTestesRepositorioComprometimento.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_comprometimento_pesquisa_todos_registros() {
		RepositorioComprometimento repositorio = Registro.obterRepositorioComprometimento();

		List<Comprometimento> comprometimentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(comprometimentoObtidas.size(), 2);
	}
}
