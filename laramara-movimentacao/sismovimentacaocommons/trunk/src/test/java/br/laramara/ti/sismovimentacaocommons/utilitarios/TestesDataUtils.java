package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesDataUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_instancia_nova_data_com_mes_correto() {
		Calendar dataEsperada = new GregorianCalendar(1982, Calendar.JULY, 27);
		Calendar dataObtida = DataHoraUtils.criar(27, 07, 1982);

		Assert.assertEquals(dataObtida, dataEsperada);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_hora() {
		Calendar data = DataHoraUtils.criar(27, 07, 1982);
		String dataFormatada = DataHoraUtils.formatarDataHoraMinutoSegundo(data);

		Assert.assertEquals(dataFormatada, "27/07/1982 00:00:00");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_date() {
		Date data = DataHoraUtils.criar(27, 07, 1982).getTime();
		String dataFormatada = DataHoraUtils.formatarData(data);

		Assert.assertEquals(dataFormatada, "27/07/1982");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_calendar() {
		Calendar data = DataHoraUtils.criar(27, 07, 1982);
		String dataFormatada = DataHoraUtils.formatarData(data);

		Assert.assertEquals(dataFormatada, "27/07/1982");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_vazia() {
		String dataFormatada = DataHoraUtils.formatarDataHoraMinutoSegundo(null);

		Assert.assertEquals(dataFormatada, "");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_instancia_nova_data_a_partir_de_texto() {
		Calendar dataObtida = DataHoraUtils.criar("31/12/1982");
		Calendar dataEsperada = DataHoraUtils.criar(31, 12, 1982);

		Assert.assertEquals(dataObtida, dataEsperada);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_instancia_nova_data_hora_a_partir_de_texto_sem_segundos() {
		Calendar data = DataHoraUtils.criar("31/12/1982 22:30");
		Assert.assertEquals(data, DataHoraUtils.criar(31, 12, 1982, 22, 30, 00));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_valida_a_partir_de_texto() {

		Assert.assertFalse(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"31/12/2011").equals(DataHoraUtils.obterDadaInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_invalida_a_partir_de_texto() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"99/02/2011").equals(DataHoraUtils.obterDadaInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_invalida_a_partir_de_texto_com_espaco() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"01/02/20  ").equals(DataHoraUtils.obterDadaInvalida()));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_hora_valira_a_partir_de_texto_com_espaco() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"01/02/20  ").equals(DataHoraUtils.obterDadaInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_retorna_tempo_decorrido() {
		Assert.assertEquals(
				Time.valueOf("04:00:00"),
				DataHoraUtils.obterTempoDecorrido(
						DataHoraUtils.obterTempo("08:00"),
						DataHoraUtils.obterTempo("12:00")));
		Assert.assertEquals(
				Time.valueOf("13:00:00"),
				DataHoraUtils.obterTempoDecorrido(
						DataHoraUtils.obterTempo("02:30"),
						DataHoraUtils.obterTempo("15:30")));
		Assert.assertEquals(
				Time.valueOf("23:59:00"),
				DataHoraUtils.obterTempoDecorrido(
						DataHoraUtils.obterTempo("00:00"),
						DataHoraUtils.obterTempo("23:59")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_retorna_hora() {

		Assert.assertEquals(DataHoraUtils.obterHora("124:50:00.000"), "124:50");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_retorna_hora_total_valida() {

		Assert.assertEquals(DataHoraUtils.retornaTotalHoraOuInvalido("124:50"),
				"124:50:00.000");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_retorna_hora_total_invalida() {

		Assert.assertNull(DataHoraUtils
				.retornaTotalHoraOuInvalido("1222224:50"));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_valida() {

		Calendar data = DataHoraUtils.criar(31, 12, 2012);
		Date dataEsperada = data.getTime();

		Assert.assertEquals(dataEsperada,
				DataHoraUtils.obterDataOuNulo("31/12/2012"));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_hora_valida() {

		Calendar data = DataHoraUtils.criar(31, 12, 2012, 23, 59, 59);
		Date dataEsperada = data.getTime();

		Assert.assertEquals(dataEsperada,
				DataHoraUtils.obterDataOuNulo("31/12/2012 23:59:59"));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_nula() {

		Assert.assertNull(DataHoraUtils.obterDataOuNulo(null));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_invalida() {

		Assert.assertNotNull(DataHoraUtils.obterDadaInvalida());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_esta_preenchida() {

		Assert.assertNotNull(DataHoraUtils
				.obterDataValidaInvalidaOuNulo("xx/ss/11"));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_nao_esta_preenchida() {

		Assert.assertNull(DataHoraUtils.obterDataValidaInvalidaOuNulo(null));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_invalida_a_partir_calendar() {

		Assert.assertTrue(DataHoraUtils.dataInvalida(DataHoraUtils
				.obterDadaInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_termino_e_anterior_a_data_inicio() {

		Assert.assertTrue(DataHoraUtils.dataTerminoAnteriorDataInicio(
				DataHoraUtils.criar("10/01/2012"),
				DataHoraUtils.criar("01/01/2012")));
		Assert.assertFalse(DataHoraUtils.dataTerminoAnteriorDataInicio(
				DataHoraUtils.criar("01/01/2012"),
				DataHoraUtils.criar("01/12/2012")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_hora_termino_e_anterior_hora_inicio() {

		Assert.assertTrue(DataHoraUtils.horaTerminoAnteriorHoraInicio(
				DataHoraUtils.obterTempo("08:00"),
				DataHoraUtils.obterTempo("04:00")));
		Assert.assertFalse(DataHoraUtils.horaTerminoAnteriorHoraInicio(
				DataHoraUtils.obterTempo("08:00"),
				DataHoraUtils.obterTempo("12:00")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_inicio_e_anterior_a_data_atual_sistema() {

		Assert.assertTrue(DataHoraUtils.dataAnteriorDataAtual(DataHoraUtils
				.criar("01/01/0001")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_inicio_nao_e_anterior_a_data_atual_sistema() {

		Assert.assertFalse(DataHoraUtils.dataAnteriorDataAtual(DataHoraUtils
				.criar("01/01/2998")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_hora() {
		String textoHora = "09:40";
		Time hora = DataHoraUtils.obterTempo(textoHora);
		String horaObtida = DataHoraUtils.obterHora(hora);

		Assert.assertEquals(textoHora, horaObtida);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_hora_invalida_pequena() {
		String textoHora = "1230";

		Assert.assertNull(DataHoraUtils.obterTempo(textoHora));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_hora_invalida_com_barra() {
		String textoHora = "12/30";

		Assert.assertNull(DataHoraUtils.obterTempo(textoHora));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_hora_nula() {

		Assert.assertTrue(DataHoraUtils.horaInvalida(null));
	}
}
