package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquidaDemanda;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioDemanda extends TestesIntegracaoAbstrato {

	public TestesRepositorioDemanda() {
		super("DadosTestesRepositorioDemanda.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_demanda_pesquisa_com_especificacao() {
		EspecificacaoPesquisaDemanda especificacao = ContextoEspecificacaoPesquidaDemanda
				.fabricarDominioComTodosOsDados();

		RepositorioDemanda repositorio = Registro.obterRepositorioDemanda();

		List<Demanda> demandaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(demandaObtidos.size(), 1);
	}
}
