package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioMunicipio extends TestesIntegracaoAbstrato {
	
	public TestesRepositorioMunicipio() {
		super("DadosTestesRepositorioMunicipio.xml");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_municipio_pesquisa_por_estado(){
		RepositorioMunicipio repositorio = Registro.obterRepositorioMunicipio();
		UF uf = UF.SP;
		
		List<Municipio> municipiosObtidos = repositorio.pesquisarPor(uf);
		
		Assert.assertEquals(municipiosObtidos.size(), 3);
	}
}
