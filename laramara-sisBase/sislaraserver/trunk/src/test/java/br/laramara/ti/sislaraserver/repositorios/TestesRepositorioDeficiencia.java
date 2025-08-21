package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioDeficiencia extends TestesIntegracaoAbstrato{
	
	public TestesRepositorioDeficiencia() {
		super("DadosTestesRepositorioDeficiencia.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_deficiencia_pesquisa_todos_registros() {
		RepositorioDeficiencia repositorio = Registro.obterRepositorioDeficiencia();

		List<Deficiencia> deficienciaObtidas = repositorio.obterTodos();

		Assert.assertEquals(deficienciaObtidas.size(), 2);
	}
}
