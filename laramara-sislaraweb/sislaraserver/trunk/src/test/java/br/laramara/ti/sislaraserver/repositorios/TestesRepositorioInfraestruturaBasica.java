package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioInfraestruturaBasica extends TestesIntegracaoAbstrato {

	public TestesRepositorioInfraestruturaBasica() {
		super("DadosTestesRepositorioInfraestruturaBasica.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_infraestrutura_basica_pesquisa_todos() {
		RepositorioInfraestruturaBasica repositorio = Registro.obterRepositorioSaneamentoBasico();

		List<InfraestruturaBasica> infraestruturaBasica = repositorio.obterTodos();

		Assert.assertEquals(infraestruturaBasica.size(), 2);
	}
}
