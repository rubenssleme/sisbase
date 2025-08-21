package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioRedeEncaminhamento extends TestesIntegracaoAbstrato {

	public TestesRepositorioRedeEncaminhamento() {
		super("DadosTestesRepositorioRedeEncaminhamento.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_rede_encaminhamento_pesquisa_todos_registros() {
		RepositorioRedeEncaminhamento repositorio = Registro.obterRepositorioRedeEncaminhamento();

		List<RedeEncaminhamento> redeEncaminhamentoObtidas = repositorio.obterTodos();

		Assert.assertEquals(redeEncaminhamentoObtidas.size(), 2);
	}
}
