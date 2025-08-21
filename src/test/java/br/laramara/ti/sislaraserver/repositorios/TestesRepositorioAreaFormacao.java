package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.AreaFormacao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioAreaFormacao extends TestesIntegracaoAbstrato {

	public TestesRepositorioAreaFormacao() {
		super("DadosTestesRepositorioAreaFormacao.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_area_formacao_obtem_com_sucesso()
			throws Exception {
		RepositorioAreaFormacao repositorioAreaFormacao = Registro
				.obterRepositorioAreaFormacao();

		List<AreaFormacao> areasFormacao = repositorioAreaFormacao.obterTodos();

		Assert.assertEquals(areasFormacao.size(), 2);
	}
}
