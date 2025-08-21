package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioDiretoriaEnsino extends TestesIntegracaoAbstrato {

	public TestesRepositorioDiretoriaEnsino() {
		super("DadosTestesRepositorioDiretoriaEnsino.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_diretoria_ensino_pesquisa_todos() {
		RepositorioDiretoriaEnsino repositorio = Registro
				.obterRepositorioDiretoriaEnsino();

		List<DiretoriaEnsino> diretoriaEnsinoObtidos = repositorio.obterTodos();

		Assert.assertEquals(diretoriaEnsinoObtidos.size(), 2);
	}

}
