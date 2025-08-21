package br.laramara.sistelemarketingserver.utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesMaquinaTempo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void maquina_tempo_obtem_data_com_sucesso() {
		MaquinaTempo.restaurarDataOriginal();
		LocalDateTime dataEHoraAtual = LocalDateTime.now();

		LocalDate data = MaquinaTempo.obterDataAtual();

		Assert.assertEquals(data.getDayOfMonth(), dataEHoraAtual.getDayOfMonth());
		Assert.assertEquals(data.getMonthValue(), dataEHoraAtual.getMonthValue());
		Assert.assertEquals(data.getYear(), dataEHoraAtual.getYear());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void maquina_tempo_obtem_data_e_hora_com_sucesso() {
		MaquinaTempo.restaurarDataOriginal();
		LocalDateTime dataEHoraAtual = LocalDateTime.now();

		LocalDateTime dataEHora = MaquinaTempo.obterDataEHoraAtual();

		Assert.assertEquals(dataEHora.getSecond(), dataEHoraAtual.getSecond());
		Assert.assertEquals(dataEHora.getMinute(), dataEHoraAtual.getMinute());
		Assert.assertEquals(dataEHora.getHour(), dataEHoraAtual.getHour());
		Assert.assertEquals(dataEHora.getDayOfMonth(), dataEHoraAtual.getDayOfMonth());
		Assert.assertEquals(dataEHora.getMonthValue(), dataEHoraAtual.getMonthValue());
		Assert.assertEquals(dataEHora.getYear(), dataEHoraAtual.getYear());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void maquina_tempo_muda_data_atual_com_sucesso() {
		String textoData = "31/12/2018";

		MaquinaTempo.mudarDataAtual(textoData);

		LocalDate data = MaquinaTempo.obterDataAtual();

		Assert.assertEquals(data.getDayOfMonth(), 31);
		Assert.assertEquals(data.getMonthValue(), 12);
		Assert.assertEquals(data.getYear(), 2018);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void maquina_tempo_restaura_tempo_original_com_sucesso() {
		String textoData = "31/12/2017";

		MaquinaTempo.mudarDataAtual(textoData);
		MaquinaTempo.restaurarDataOriginal();

		LocalDate data = MaquinaTempo.obterDataAtual();

		Assert.assertEquals(data, LocalDate.now());
	}
}
