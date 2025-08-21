package br.laramara.ti.sislaraserver.repositorios.externo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoUsuarioExterno;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuarioExterno;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioUsuarioExterno extends TestesIntegracaoAbstrato {

	private RepositorioUsuarioExterno repositorio;

	public TestesRepositorioUsuarioExterno() {
		super("DadosTestesRepositorioUsuarioExterno.xml");
		repositorio = Registro.obterRepositorioUsuarioExterno();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_salva_e_obtem_usuario_externo_cadastrado_com_sucesso() {
		UsuarioExterno usuarioExterno = repositorio
				.salvar(ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados());

		UsuarioExterno usuarioExternoObtido = repositorio.obterPorId(usuarioExterno.getId());

		Assert.assertEquals(usuarioExternoObtido, usuarioExterno);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_obtem_usuario_externo_cadastrado_por_email_com_sucesso() {
		Long id = 1111L;
		String email = "carloskafka7@gmail.com";

		UsuarioExterno usuarioExternoObtido = repositorio.obterPorEmail(email);

		Assert.assertEquals(usuarioExternoObtido.getId(), id);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_obtem_usuario_externo_cadastrado_por_credencialexterna_com_sucesso() {
		Long id = 1111L;

		CredencialExterna credencialExterna = new CredencialExterna("carloskafka7@gmail.com", "1234");
		
		UsuarioExterno usuarioExternoObtido = repositorio.obterUsuarioExterno(credencialExterna);

		Assert.assertEquals(usuarioExternoObtido.getId(), id);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_obtem_usuario_externo_cadastrado_por_token_com_sucesso() {
		String token = "1234";
		Long id = 1111L;

		UsuarioExterno usuarioExternoObtido = repositorio.obterPorToken(token);

		Assert.assertEquals(usuarioExternoObtido.getId(), id);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_valida_se_cpf_ja_existe_com_sucesso() {
		String cpf = "86890477006";

		boolean usuarioExternoObtido = repositorio.cpfJaExiste(cpf);

		Assert.assertTrue(usuarioExternoObtido);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_externo_valida_se_cpf_ja_existe_sem_sucesso() {
		String cpfInvalido = "00000000000";

		boolean usuarioExternoObtido = repositorio.cpfJaExiste(cpfInvalido);

		Assert.assertFalse(usuarioExternoObtido);
	}
}
