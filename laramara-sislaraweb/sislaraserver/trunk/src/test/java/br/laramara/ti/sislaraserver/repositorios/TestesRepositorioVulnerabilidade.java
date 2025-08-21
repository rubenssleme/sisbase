package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioVulnerabilidade extends TestesIntegracaoAbstrato {

	public TestesRepositorioVulnerabilidade() {
		super("DadosTestesRepositorioVulnerabilidade.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_vulnerabilidade_para_familia_pesquisa_todos_para_usuario() {
		RepositorioVulnerabilidade repositorio = Registro
				.obterRepositorioVulnerabilidade();

		List<Vulnerabilidade> vulnerabilidadesObtidas = repositorio.obterTodosParaFamilia();

		Assert.assertEquals(vulnerabilidadesObtidas.size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_vulnerabilidade_para_usuario_pesquisa_todos_para_usuario() {
		RepositorioVulnerabilidade repositorio = Registro
				.obterRepositorioVulnerabilidade();

		List<Vulnerabilidade> vulnerabilidadesObtidas = repositorio.obterTodosParaUsuario();

		Assert.assertEquals(vulnerabilidadesObtidas.size(), 1);
	}

}
