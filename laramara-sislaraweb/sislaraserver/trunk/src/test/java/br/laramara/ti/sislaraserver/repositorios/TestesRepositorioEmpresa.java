package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioEmpresa extends TestesIntegracaoAbstrato {

	public TestesRepositorioEmpresa() {
		super("DadosTestesRepositorioEmpresa.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_empresa_pesquisa_todos_registros() {
		RepositorioEmpresa repositorio = Registro.obterRepositorioEmpresa();

		List<Empresa> empresaObtidas = repositorio.obterTodos();

		Assert.assertEquals(empresaObtidas.size(), 2);
	}

}
