package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoGuarda;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioSituacaoGuarda extends TestesIntegracaoAbstrato {

	public TestesRepositorioSituacaoGuarda() {
		super("DadosTestesRepositorioSituacaoGuarda.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_situacaoguarda_pesquisa_todos_registros() {
		RepositorioSituacaoGuarda repositorio = Registro
				.obterRepositorioSituacaoGuarda();

		List<SituacaoGuarda> situacaoGuardaObtidas = repositorio.obterTodos();

		Assert.assertEquals(situacaoGuardaObtidas.size(), 2);
	}
}
