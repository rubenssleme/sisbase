package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.seguranca.Area;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioSislara extends TestesIntegracaoAbstrato {

	public TestesRepositorioSislara() {
		super("DadosTestesRepositorioSislara.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_sislara_pesquisa_frequencias() {
		RepositorioSislara repositorio = Registro.obterRepositorioSislara();

		List<Map<String, Object>> frequenciasObtidas = repositorio.obterFrenquenciaPorProntuarioExcluindoArea(
				new Long(12222), Area.LIBERACAO_INCLUSAO_NA_ESPERA_POR_EXCESSO_DE_FALTA);

		Assert.assertEquals(frequenciasObtidas.size(), 2);
	}
}
