package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

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
		perfil.adicionarPermissao(Permissao.CONTA_ACESSO_EDICAO);
		
		Perfil perfisSalvo = repositorio.salvar(perfil);

		Assert.assertEquals(perfisSalvo.getPermissoes().size(), 2);
	}
}
