package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecibo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioRecibo extends TestesIntegracaoAbstrato {

	private RepositorioRecibo repositorio;

	public TestesRepositorioRecibo() {
		super("DadosTestesRepositorioRecibo.xml");
		repositorio = Registro.obterRepositorioRecibo();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_recibo_salva() {
		Recibo reciboASalvar = ContextoRecibo.fabricarRecibo();
		reciboASalvar.setId(null);

		Recibo recibosSalvo = repositorio.salvar(reciboASalvar);

		Assert.assertNotNull(recibosSalvo.getId());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_recibo_obtem_por_id() {
		
		Long id = new Long(9999);
		
		Recibo recibo = repositorio.obterPorId(id);

		Assert.assertEquals(recibo.getId(), id);
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_recibo_obtem_todos() {
		
		List<Recibo> recibos = repositorio.obterTodos();

		Assert.assertEquals(recibos.size(), 1);
	}
}
