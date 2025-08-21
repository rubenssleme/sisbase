package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspera;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioBloqueio extends TestesIntegracaoAbstrato {

	public TestesRepositorioBloqueio() {
		super("DadosTestesRepositorioBloqueio.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_bloqueio_pesquisa_com_sucesso() {
		RepositorioBloqueio repositorio = Registro.obterRepositorioBloqueio();

		Espera espera = ContextoEspera.fabricarEsperaComTodosOsDados();

		boolean existeBloqueio = repositorio.existeBloqueioListaEspera(espera);

		Assert.assertTrue(existeBloqueio);
	}
}
