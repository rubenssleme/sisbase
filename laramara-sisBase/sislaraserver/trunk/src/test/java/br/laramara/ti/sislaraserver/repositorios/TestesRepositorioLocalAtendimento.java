package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioLocalAtendimento extends TestesIntegracaoAbstrato {

	public TestesRepositorioLocalAtendimento() {
		super("DadosTestesRepositorioLocalAtendimento.xml");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_localatendimento_pesquisa_todos() {
		RepositorioLocalAtendimento repositorio = Registro
				.obterRepositorioLocalAtendimento();

		List<LocalAtendimento> localAtendimentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(localAtendimentoObtidas.size(), 2);
	}
}
