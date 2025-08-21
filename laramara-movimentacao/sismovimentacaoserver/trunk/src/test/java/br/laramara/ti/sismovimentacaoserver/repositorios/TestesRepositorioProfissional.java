package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class TestesRepositorioProfissional extends TestesIntegracaoAbstrato {

	public TestesRepositorioProfissional() {
		super("DadosTestesRepositorioProfissional.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_profissional_pesquisa_todos_profissionais_habilitados() {
		RepositorioProfissional repositorio = Registro
				.obterRepositorioProfissional();

		List<Profissional> profissionalObtidos = repositorio.obterProfissionaisAtivos();

		Assert.assertEquals(profissionalObtidos.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_profissional_pesquisa_todos_voluntarios_habilitados() {
		RepositorioProfissional repositorio = Registro
				.obterRepositorioProfissional();

		List<Profissional> profissionalObtidos = repositorio.obterVoluntarioAtivo();

		Assert.assertEquals(profissionalObtidos.size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_profissional_cadastra_com_sucesso() {
		RepositorioProfissional repositorio = Registro
				.obterRepositorioProfissional();

		Profissional profissionalASalvar = new Profissional(null,
				"Paulo Augusto", "12345");
		repositorio.salvar(profissionalASalvar);

		Assert.assertEquals(repositorio.obterProfissionaisAtivos().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_profissional_altera_com_sucesso() {
		String novoNome = "NOME APOS ALTERACAO";
		RepositorioProfissional repositorio = Registro
				.obterRepositorioProfissional();

		Profissional profissionalAAlterar = repositorio.obterProfissionaisAtivos().get(0);
		profissionalAAlterar.setNome(novoNome);

		repositorio.salvar(profissionalAAlterar);

		Profissional profissionalAposAlteracao = repositorio.obterProfissionaisAtivos()
				.get(0);

		Assert.assertEquals(profissionalAposAlteracao.getNome(),
				profissionalAAlterar.getNome());
	}
}
