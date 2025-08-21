package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioFamiliar extends TestesIntegracaoAbstrato {

	public TestesRepositorioFamiliar(){
		super("DadosTestesRepositorioFamiliar.xml");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_familiar_pesquisa_por_id() {
		RepositorioFamiliar repositorio = Registro.obterRepositorioFamiliar();

		Long id = new Long(44444);
		
		Familiar beneficioObtidas = repositorio.obterPorId(id);

		Assert.assertNotNull(beneficioObtidas);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_familiar_pesquisa_por_id_inexistente() {
		RepositorioFamiliar repositorio = Registro.obterRepositorioFamiliar();

		Long id = new Long(23434);
		
		Familiar beneficioObtidas = repositorio.obterPorId(id);

		Assert.assertNull(beneficioObtidas);
	}
}
