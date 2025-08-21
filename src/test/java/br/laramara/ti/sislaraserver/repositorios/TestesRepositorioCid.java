package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioCid extends TestesIntegracaoAbstrato {

	public TestesRepositorioCid() {
		super("DadosTestesRepositorioCid.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_cid_obtem_com_sucesso() throws Exception {
		RepositorioCid repositorioCid = Registro.obterRepositorioCid();

		Cid cid = repositorioCid.obterPorCid("A001");

		Assert.assertNotNull(cid);
	}
}
