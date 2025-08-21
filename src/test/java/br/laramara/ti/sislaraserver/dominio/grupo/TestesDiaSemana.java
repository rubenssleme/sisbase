package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDiaSemana {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void diasemana_obtem_a_partir_data() {
		Calendar data = DataHoraUtils.criar("28/04/2013");

		Assert.assertTrue(DiaSemana.obterDiaSemana(data).equals(
				DiaSemana.DOMINGO));
	}
}
