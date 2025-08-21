package br.laramara.ti.sislaraserver.repositorios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioInformacaoEssencial extends
		TestesIntegracaoAbstrato {

	public TestesRepositorioInformacaoEssencial() {
		super("DadosTestesRepositorioInformacaoEssencial.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_informacao_essencial_obtem_por_id() {
		RepositorioInformacaoEssencial repositorio = Registro
				.obterRepositorioInformacaoEssencial();

		InformacaoEssencial informacaoEssencialObtida = repositorio
				.obterPorId(new Long(9999));

		Assert.assertNotNull(informacaoEssencialObtida);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_informacao_essencial_obtem_por_id_inexistente() {
		RepositorioInformacaoEssencial repositorio = Registro
				.obterRepositorioInformacaoEssencial();

		InformacaoEssencial informacaoEssencialObtida = repositorio
				.obterPorId(new Long(1212));

		Assert.assertNull(informacaoEssencialObtida);
	}

}
