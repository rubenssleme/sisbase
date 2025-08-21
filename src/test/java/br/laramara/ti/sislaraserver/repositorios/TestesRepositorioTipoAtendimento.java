package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioTipoAtendimento extends TestesIntegracaoAbstrato {

	public TestesRepositorioTipoAtendimento() {
		super("DadosTestesRepositorioTipoAtendimento.xml");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_tipoatendimento_pesquisa_todos_tipoatendimentos() {

		RepositorioTipoAtendimento repositorio = Registro
				.obterRepositorioTipoAtendimento();

		List<TipoAtendimento> tipoAtendimentoObtidas = repositorio
				.obterTodos();

		Assert.assertEquals(tipoAtendimentoObtidas.size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_tipoatendimento_pesquisa_todos_descricao_tipo_endimentos_ativos() {

		RepositorioTipoAtendimento repositorio = Registro
				.obterRepositorioTipoAtendimento();

		List<TipoAtendimento> tipoAtendimentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(tipoAtendimentoObtidas.get(0)
				.getDescricoesTipoAtendimentoAtivos().size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_tipoatendimento_pesquisa_descricao_tipo_atendimento_com_modulos() {

		RepositorioTipoAtendimento repositorio = Registro
				.obterRepositorioTipoAtendimento();

		List<TipoAtendimento> tipoAtendimentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(tipoAtendimentoObtidas.get(0)
				.getDescricoesTipoAtendimentoAtivos().get(0).getModulos().size(), 2);
	}
}
