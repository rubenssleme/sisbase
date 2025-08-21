package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioPerfil extends TestesIntegracaoAbstrato {

	public TestesRepositorioPerfil() {
		super("DadosTestesRepositorioPerfil.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_perfil_obtem_todos_perfis() {
		RepositorioPerfil repositorio = Registro.obterRepositorioPerfil();

		List<Perfil> perfisObtidas = repositorio.obterTodos();

		Assert.assertEquals(perfisObtidas.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_perfil_inclui_novo_perfil() {
		RepositorioPerfil repositorio = Registro.obterRepositorioPerfil();

		Perfil perfil = new Perfil(null, "teste");
		Perfil perfisSalvo = repositorio.salvar(perfil);

		Assert.assertNotNull(perfisSalvo.getId());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_perfil_altera_perfil() {
		RepositorioPerfil repositorio = Registro.obterRepositorioPerfil();

		List<Perfil> perfisObtidas = repositorio.obterTodos();
		Perfil perfil = perfisObtidas.get(0);
		perfil.adicionarPermissao(Permissao.PRE_CADASTRO_TELA_EDICAO_VISUALIZAR);
		
		Perfil perfisSalvo = repositorio.salvar(perfil);

		Assert.assertEquals(perfisSalvo.getPermissoes().size(), 2);
	}
}
