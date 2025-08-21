package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;
import br.laramara.ti.sislaraserver.fabricas.ContextoAcaoConduta;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioAcaoConduta extends TestesIntegracaoAbstrato {

	public TestesRepositorioAcaoConduta() {
		super("DadosTestesRepositorioAcaoConduta.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_acao_conduta_pesquisa_todos_registros() {
		RepositorioAcaoConduta repositorio = Registro.obterRepositorioAcaoConduta();

		List<AcaoConduta> acaoCondutaObtidos = repositorio.obterAcaoCondutasNaoProcessadas();

		Assert.assertEquals(acaoCondutaObtidos.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_acao_conduta_salva_com_sucesso() {
		RepositorioAcaoConduta repositorio = Registro.obterRepositorioAcaoConduta();

		AcaoConduta acaoConduta = ContextoAcaoConduta.fabricarAcaoCondutaComTodosOsDados();
		acaoConduta.setId(null);
		acaoConduta.setGrupo(null);
		acaoConduta.getUsuario().setId(new Long(12222));
		
		AcaoConduta acaoCondutaSalva = repositorio.salvar(acaoConduta);
		
		Assert.assertNotNull(acaoCondutaSalva.getId());
	}
}
