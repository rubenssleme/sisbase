package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioTipificacaoServico extends
		TestesIntegracaoAbstrato {

	public TestesRepositorioTipificacaoServico() {
		super("DadosTestesRepositorioTipificacaoServico.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_tipificacao_servico_pesquisa_todos() {
		RepositorioTipificacaoServico repositorio = Registro
				.obterRepositorioTipificacaoServico();

		List<TipificacaoServico> tipificacaoServicoObtidas = repositorio
				.obterTodos();

		Assert.assertEquals(tipificacaoServicoObtidas.size(), 2);
	}
}
