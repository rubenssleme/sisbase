package br.laramara.ti.sislaraserver.repositorios;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioUsuario extends TestesIntegracaoAbstrato {

	private RepositorioUsuario repositorio;

	public TestesRepositorioUsuario() {
		super("DadosTestesRepositorioUsuario.xml");
		repositorio = Registro.obterRepositorioUsuario();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_salva_foto_e_obtem_foto_cadastrada_com_sucesso()
			throws IOException {
		byte[] fotoASalvar = FileUtils.readFileToByteArray(new File(
				"src/test/resources/foto_teste.jpg"));
		Usuario usuario = repositorio.salvar(ContextoUsuario
				.fabricarUsuarioComTodosOsDadosESRMseSalaDeRecursoEOutrosAEE());
		usuario.getInformacaoEssencial().setNome("NOME TESTES");
		usuario.setFoto(fotoASalvar);

		Usuario usuarioSalvo = repositorio.salvar(usuario);

		Usuario usuarioObtidoPorProntuario = repositorio
				.obterPorId(usuarioSalvo.getId());
		List<Usuario> usuarioPesquisadoPorNome = repositorio
				.pesquisarPorNome(usuario.getInformacaoEssencial().getNome());
		List<Usuario> usuarioPesquisadoPorRG = repositorio
				.pesquisarPorRG(usuario.getInformacaoEssencial().getRg());

		Assert.assertEquals(usuarioObtidoPorProntuario.getFoto().length,
				fotoASalvar.length);
		Assert.assertEquals(usuarioPesquisadoPorNome.get(0).getFoto().length,
				fotoASalvar.length);
		Assert.assertEquals(usuarioPesquisadoPorRG.get(0).getFoto().length,
				fotoASalvar.length);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_salva_e_obtem_usuario_cadastrado_com_sucesso() {
		Usuario usuario = repositorio.salvar(ContextoUsuario
				.fabricarUsuarioComTodosOsDadosESRMseSalaDeRecursoEOutrosAEE());

		Usuario usuarioObtido = repositorio.obterPorId(usuario
				.getId());

		Assert.assertEquals(usuarioObtido, usuario);
		Assert.assertEquals(usuarioObtido.getClassificacaoSocial(),
				usuario.getClassificacaoSocial());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getNome(),
				usuario.getInformacaoEssencial().getNome());
		Assert.assertEquals(usuarioObtido.getGenero(), usuario.getGenero());
		Assert.assertEquals(usuarioObtido.getDataNascimento(),
				usuario.getDataNascimento());
		Assert.assertEquals(usuarioObtido.getNaturalidade(),
				usuario.getNaturalidade());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getRg(),
				usuario.getInformacaoEssencial().getRg());
		Assert.assertEquals(usuarioObtido.getUfRg(), usuario.getUfRg());
		Assert.assertEquals(usuarioObtido.getDataExpedicaoRg(),
				usuario.getDataExpedicaoRg());
		Assert.assertEquals(usuarioObtido.getOrgaoEmissorRg(),
				usuario.getOrgaoEmissorRg());
		Assert.assertEquals(usuarioObtido.getEstadoCivil(),
				usuario.getEstadoCivil());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getUf(), usuario.getInformacaoEssencial().getUf());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getCep(), usuario.getInformacaoEssencial().getCep());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getMunicipio(),
				usuario.getInformacaoEssencial().getMunicipio());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getBairro(), usuario.getInformacaoEssencial().getBairro());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getEndereco(), usuario.getInformacaoEssencial().getEndereco());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getNumero(), usuario.getInformacaoEssencial().getNumero());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getComplemento(),
				usuario.getInformacaoEssencial().getComplemento());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getContato()
				.getTelefones().size(), 3);
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getContato()
				.getNomeContato(), usuario.getInformacaoEssencial()
				.getContato().getNomeContato());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getContato()
				.getEmail(), usuario.getInformacaoEssencial().getContato()
				.getEmail());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getZona(), usuario.getInformacaoEssencial().getZona());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getPais(), usuario.getInformacaoEssencial().getPais());
		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getPais().getNome(), usuario.getInformacaoEssencial()
				.getPais().getNome());
		Assert.assertEquals(usuarioObtido.getFamiliares().size(), 2);
		Assert.assertEquals(
				usuarioObtido.obterInformacoesEducacionais().size(), 1);
		Assert.assertEquals(usuarioObtido.getInstituicaoComSRMs().getId(),
				usuario.getInstituicaoComSRMs().getId());
		Assert.assertEquals(usuarioObtido.getInstituicaoComSalaRecurso()
				.getId(), usuario.getInstituicaoComSalaRecurso().getId());
		Assert.assertEquals(usuarioObtido.getSituacoesGuarda().size(), 1);
		Assert.assertEquals(usuarioObtido.getPeriodoBeneficios().size(), 1);
		Assert.assertEquals(usuarioObtido.getPeriodoDeficiencias().size(), 1);
		Assert.assertEquals(usuarioObtido.getConvenios().size(), 1);
		Assert.assertTrue(usuarioObtido.possuiVigenciaAtivaProceja());
		Assert.assertEquals(usuarioObtido.getCertidoes().size(), 1);
		Assert.assertEquals(usuarioObtido.possuiConsanguinidade(), usuario.possuiConsanguinidade());
		Assert.assertEquals(usuarioObtido.getServicos().size(), usuario.getServicos().size());	
		Assert.assertEquals(usuarioObtido.getRecursosProximoAMoradia().size(), usuario.getRecursosProximoAMoradia().size());	
		Assert.assertEquals(usuarioObtido.getNecessidades().size(), usuario.getNecessidades().size());	
		Assert.assertEquals(usuarioObtido.getExpectativas().size(), usuario.getExpectativas().size());	
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_altera_usuario_existente_com_sucesso() {
		String novoNomeUsuario = "Novo nome do usuário";
		String novoNomeFamiliar = "Novo nome do familiar";

		Usuario usuario = repositorio.salvar(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());

		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setNome(novoNomeUsuario);

		usuario.setInformacaoEssencial(informacaoEssencial);
		usuario.getFamiliares().get(0).getInformacaoEssencial()
				.setNome(novoNomeFamiliar);
		usuario.getFamiliares().remove(1);

		Usuario usuarioObtido = repositorio.salvar(usuario);

		Assert.assertEquals(usuarioObtido.getInformacaoEssencial().getNome(),
				novoNomeUsuario);
		Assert.assertEquals(usuarioObtido.getFamiliares().get(0)
				.getInformacaoEssencial().getNome(), novoNomeFamiliar);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_tenta_obter_usuario_inexistente_atraves_do_prontuario() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();

		Usuario usuarioInexistente = repositorioUsuario
				.obterPorId(new Long(32983289));

		Assert.assertNull(usuarioInexistente);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuarios_existentes_por_nome() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String nomeAPesquisar = "Augusto";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorNome(nomeAPesquisar);

		Assert.assertEquals(usuariosObtido.size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuarios_existentes_por_familiar() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String nomeAPesquisar = "Usuario";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorFamiliar(nomeAPesquisar);

		Assert.assertEquals(usuariosObtido.size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuario_inexistente_por_nome() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String nomeAPesquisar = "Silveira";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorNome(nomeAPesquisar);

		Assert.assertEquals(usuariosObtido.size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuario_existente_por_rg() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String rgAPesquisar = "123456";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorRG(rgAPesquisar);

		Assert.assertEquals(usuariosObtido.size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuario_com_informacao_educacional() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String nomeAPesquisar = "Jose Augusto Siqueira";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorNome(nomeAPesquisar);

		Assert.assertNotNull(usuariosObtido.get(0)
				.obterInformacoesEducacionais().get(0).getId());
		Assert.assertNotNull(usuariosObtido.get(0)
				.obterInformacoesEducacionais().get(0).getInstituicao());
		Assert.assertNotNull(usuariosObtido.get(0)
				.obterInformacoesEducacionais().get(0).getEscolaridade());
		Assert.assertNotNull(usuariosObtido.get(0)
				.obterInformacoesEducacionais().get(0).getPeriodo());
		Assert.assertNotNull(usuariosObtido.get(0)
				.obterInformacoesEducacionais().get(0).getSituacao());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_usuario_pesquisa_usuario_com_nome_com_acento() {
		RepositorioUsuario repositorioUsuario = Registro
				.obterRepositorioUsuario();
		String nomeAPesquisar = "Jose Augusto Siqueira";

		List<Usuario> usuariosObtido = repositorioUsuario
				.pesquisarPorNome(nomeAPesquisar);

		Assert.assertFalse(usuariosObtido.isEmpty());
	}
}
