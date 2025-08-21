package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioBeneficio extends TestesIntegracaoAbstrato {

	public TestesRepositorioBeneficio() {
		super("DadosTestesRepositorioBeneficio.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_beneficio_pesquisa_todos_registros() {
		RepositorioBeneficio repositorio = Registro.obterRepositorioBeneficio();

		List<Beneficio> beneficioObtidas = repositorio.obterTodos();

		Assert.assertEquals(beneficioObtidas.size(), 2);
	}
}
