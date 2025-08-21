package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioNecessidade extends TestesIntegracaoAbstrato {

		public TestesRepositorioNecessidade() {
			super("DadosTestesRepositorioNecessidade.xml");
		}

		@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
		public void repositorio_necessidade_pesquisa_todos() {
			RepositorioNecessidade repositorio = Registro.obterRepositorioNecessidade();

			List<Necessidade> servicos = repositorio.obterTodos();

			Assert.assertEquals(servicos.size(), 2);
		}
}
