package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioConvenio extends TestesIntegracaoAbstrato {

	public TestesRepositorioConvenio() {
		super("DadosTestesRepositorioConvenio.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_convenio_pesquisa_todos_registros() {
		RepositorioConvenio repositorio = Registro.obterRepositorioConvenio();

		List<Convenio> convenioObtidas = repositorio.obterTodos();

		Assert.assertEquals(convenioObtidas.size(), 2);
	}
}
