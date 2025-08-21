package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioInformacaoEducacional extends
		TestesIntegracaoAbstrato {

	public TestesRepositorioInformacaoEducacional() {
		super("DadosTestesFabricaInformacaoEducacional.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_informacao_educacional_obtem_por_id() {
		RepositorioInformacaoEducacional repositorio = Registro
				.obterRepositorioInformacaoEscolar();

		InformacaoEducacional informacaoEducacionalObtida = repositorio
				.obterPorId(new Long(88888));

		Assert.assertNotNull(informacaoEducacionalObtida);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_informacao_educacional_obtem_por_id_inexistente() {
		RepositorioInformacaoEducacional repositorio = Registro
				.obterRepositorioInformacaoEscolar();

		InformacaoEducacional informacaoEducacionalObtida = repositorio
				.obterPorId(new Long(1212));

		Assert.assertNull(informacaoEducacionalObtida);
	}
}
