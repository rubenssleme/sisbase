package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuarioExterno;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioUsuarioExterno extends TestesIntegracaoAbstrato {

	private RepositorioUsuarioExterno repositorio;

	public TestesRepositorioUsuarioExterno() {
		super("DadosTestesRepositorioUsuarioExterno.xml");
		repositorio = Registro.obterRepositorioUsuarioExterno();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_salva_e_obtem_usuario_externo_cadastrado_com_sucesso() {
		UsuarioExterno usuarioExterno = repositorio.salvar(ContextoUsuarioExterno
				.fabricarUsuarioExternoComTodosOsDados());

		UsuarioExterno usuarioExternoObtido = repositorio.obterPorId(usuarioExterno
				.getId());

		Assert.assertEquals(usuarioExternoObtido, usuarioExterno);
		Assert.assertEquals(usuarioExternoObtido.getEmail(),
				usuarioExterno.getEmail());
		Assert.assertEquals(usuarioExternoObtido.getSenha(),
				usuarioExterno.getSenha());
		Assert.assertEquals(usuarioExternoObtido.isBloqueado(),
				usuarioExterno.isBloqueado());
		Assert.assertEquals(usuarioExternoObtido.getToken(),
				usuarioExterno.getToken());
	}

}
