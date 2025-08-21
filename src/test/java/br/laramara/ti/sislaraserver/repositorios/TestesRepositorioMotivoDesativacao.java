package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.contribuicao.MotivoDesativacao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioMotivoDesativacao extends TestesIntegracaoAbstrato {

	public TestesRepositorioMotivoDesativacao() {
		super("DadosTestesRepositorioMotivoDesativacao.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_motivo_desativacao_pesquisa_todos_registros() {
		RepositorioMotivoDesativacao repositorio = Registro
				.obterRepositorioMotivoDesativacao();

		List<MotivoDesativacao> motivoDesativacao = repositorio.obterTodos();

		Assert.assertEquals(motivoDesativacao.size(), 2);
	}
}
