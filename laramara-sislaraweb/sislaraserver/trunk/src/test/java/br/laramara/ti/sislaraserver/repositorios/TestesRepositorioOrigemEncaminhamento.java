package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioOrigemEncaminhamento extends TestesIntegracaoAbstrato {

	public TestesRepositorioOrigemEncaminhamento() {
		super("DadosTestesRepositorioOrigemEncaminhamento.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_origem_encaminhamento_pesquisa_todos_registros() {
		RepositorioOrigemEncaminhamento repositorio = Registro.obterRepositorioOrigemEncaminhamento();

		List<OrigemEncaminhamento> origemEncaminhamentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(origemEncaminhamentoObtidas.size(), 2);
	}
}
