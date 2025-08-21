package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Servico;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioServico extends TestesIntegracaoAbstrato {

	public TestesRepositorioServico() {
		super("DadosTestesRepositorioServico.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_servico_pesquisa_todos() {
		RepositorioServico repositorio = Registro.obterRepositorioServico();

		List<Servico> servicos = repositorio.obterTodos();

		Assert.assertEquals(servicos.size(), 2);
	}

}
