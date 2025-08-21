package br.laramara.ti.sislaracommons.utilitarios;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestesDataUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_adiciona_dias_em_data_atual() {
		Calendar data = DataHoraUtils.criar(01, 01, 2000);

		Calendar dataApos120Dias = DataHoraUtils.obterDataAposDias(data, 31);

		Assert.assertEquals(DataHoraUtils.formatarData(dataApos120Dias), "01/02/2000");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_instancia_nova_data_com_mes_correto() {
		Calendar dataEsperada = new GregorianCalendar(1982, Calendar.JULY, 27);
		Calendar dataObtida = DataHoraUtils.criar(27, 07, 1982);

		Assert.assertEquals(dataObtida, dataEsperada);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_hora() {
		Calendar data = DataHoraUtils.criar(27, 07, 1982);
		String dataFormatada = DataHoraUtils.formatarDataHora(data);

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
	public void datautils_formata_data_local_date() {
		LocalDate data = LocalDate.of(1982, 07, 27);
		String dataFormatada = DataHoraUtils.formatarData(data);

		Assert.assertEquals(dataFormatada, "27/07/1982");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_calendar_alternativo() {
		Calendar data = DataHoraUtils.criar(27, 07, 1982);
		String dataFormatada = DataHoraUtils.formatarDataCompacta(data);

		Assert.assertEquals(dataFormatada, "27/07/82");
	}


	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_formata_data_vazia() {
		String dataFormatada = DataHoraUtils.formatarDataHora(null);

		Assert.assertEquals(dataFormatada, "");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_expirou_limite_24_horas() {

		Assert.assertTrue(DataHoraUtils.expirouLimite24Horas(DataHoraUtils.criar(1, 1, 2016, 0, 0, 0),
				DataHoraUtils.obterTempo("09:00")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_nao_expirou_limite_24_horas() {

		Assert.assertFalse(DataHoraUtils.expirouLimite24Horas(Calendar.getInstance(),
				DataHoraUtils.obterTempo("09:00")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_avanca_data_em_2_anos() {

		Assert.assertEquals(DataHoraUtils.avancarDoisAnos(DataHoraUtils.criar("01/01/2000")),
				DataHoraUtils.criar("01/01/2002"));
	}


	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_instancia_nova_data_a_partir_de_texto() {
		Calendar dataObtida = DataHoraUtils.criar("31/12/1982");
		Calendar dataEsperada = DataHoraUtils.criar(31, 12, 1982);

		Assert.assertEquals(dataObtida, dataEsperada);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_valida_a_partir_de_texto() {

		Assert.assertFalse(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"31/12/2011").equals(DataHoraUtils.obterDataInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_invalida_a_partir_de_texto() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"99/02/2011").equals(DataHoraUtils.obterDataInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_invalida_a_partir_de_texto_com_espaco() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"01/02/20  ").equals(DataHoraUtils.obterDataInvalida()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_data_hora_valira_a_partir_de_texto_com_espaco() {

		Assert.assertTrue(DataHoraUtils.obterDataValidaInvalidaOuNulo(
				"01/02/20  ").equals(DataHoraUtils.obterDataInvalida()));
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
	public void datautils_obtem_data_hora_alternativo_valida() {

		Calendar data = DataHoraUtils.criar(1, 1, 2012, 0, 0, 0);
		Date dataEsperada = data.getTime();

		Assert.assertEquals(dataEsperada,
				DataHoraUtils.obterDataOuNulo("01/01/2012 00:00:00"));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_nula() {

		Assert.assertNull(DataHoraUtils.obterDataOuNulo(null));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_invalida() {

		Assert.assertNotNull(DataHoraUtils.obterDataInvalida());
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
				.obterDataInvalida()));
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
	public void datautils_verifica_se_data_igual_ou_anterior_a_data_inicio() {

		Assert.assertTrue(DataHoraUtils.dataIgualOuAnterior(
				DataHoraUtils.criar("10/01/2012"),
				DataHoraUtils.criar("10/01/2012")));
		Assert.assertTrue(DataHoraUtils.dataIgualOuAnterior(
				DataHoraUtils.criar("09/01/2012"),
				DataHoraUtils.criar("10/01/2012")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_igual_ou_posterior_a_data_inicio() {

		Assert.assertTrue(DataHoraUtils.dataIgualOuPosterior(
				DataHoraUtils.criar("10/01/2012"),
				DataHoraUtils.criar("10/01/2012")));
		Assert.assertTrue(DataHoraUtils.dataIgualOuPosterior(
				DataHoraUtils.criar("11/01/2012"),
				DataHoraUtils.criar("10/01/2012")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_anos_transcorridos() {
		int idade = 33;
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		Calendar dataNascimento = DataHoraUtils.criar(01, 01, anoAtual - idade);

		Assert.assertEquals(
				DataHoraUtils.obterAnosTranscorridos(dataNascimento), idade);
	}
	
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_anos_transcorridos_alternativo() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		Calendar dataAnterior = DataHoraUtils.criar(01, 01, ano);
		Calendar dataAtual = DataHoraUtils.criar(01, 01, ano + 2);

		Assert.assertEquals(DataHoraUtils.obterAnosTranscorridos(dataAnterior, dataAtual), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_anos_transcorridos_sem_sucesso() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		Calendar dataAnterior = DataHoraUtils.criar(01, 01, ano);
		Calendar dataAtual = DataHoraUtils.criar(01, 01, ano + 0);

		Assert.assertEquals(DataHoraUtils.obterAnosTranscorridos(dataAnterior, dataAtual), 0);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_meses_transcorridos() {
		int meses = 24;
		Calendar dataNascimento = DataHoraUtils.criar("01/01/2011");
		MaquinaTempo.mudarDataAtual("01/01/2013");
		int mesesTranscorridos = DataHoraUtils.obterMesesTranscorridos(dataNascimento);
		MaquinaTempo.restaurarDataOriginal();
		Assert.assertEquals(mesesTranscorridos, meses);
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
	public void datautils_verifica_se_data_inicio_e_posterior_a_data_atual_sistema() {

		Assert.assertTrue(DataHoraUtils.dataPosteriorDataAtual(DataHoraUtils
				.criar("01/01/2090")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_inicio_nao_e_anterior_a_data_atual_sistema() {

		Assert.assertFalse(DataHoraUtils.dataAnteriorDataAtual(DataHoraUtils
				.criar("01/01/2998")));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_verifica_se_data_inicio_nao_e_posterior_a_data_atual_sistema() {

		Assert.assertFalse(DataHoraUtils.dataPosteriorDataAtual(DataHoraUtils
				.criar("01/01/1998")));
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

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_apos_meio_periodo() {
		Assert.assertFalse(DataHoraUtils.aposMeioPeriodo(DataHoraUtils.obterTempo("13:59")));
		Assert.assertTrue(DataHoraUtils.aposMeioPeriodo(DataHoraUtils.obterTempo("14:00")));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_cria_local_date_time_com_sucesso() {

		int dia = 31;
		int mes = 12;
		int ano = 2000;
		int hora = 23;
		int minuto = 59;

		Calendar data = DataHoraUtils.criar(dia, mes, ano, hora, minuto, 0);

		LocalDateTime localDateTime = DataHoraUtils.criar(data, DataHoraUtils.obterTempo("23:59"));

		Assert.assertTrue(localDateTime.getDayOfMonth() == dia);
		Assert.assertTrue(localDateTime.getMonthValue() == mes);
		Assert.assertTrue(localDateTime.getYear() == ano);
		Assert.assertTrue(localDateTime.getHour() == hora);
		Assert.assertTrue(localDateTime.getMinute() == minuto);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_cria_local_date_time_alternativo_com_sucesso() {

		int dia = 1;
		int mes = 1;
		int ano = 2000;
		int hora = 0;
		int minuto = 0;

		Calendar data = DataHoraUtils.criar(dia, mes, ano, hora, minuto, 0);

		LocalDateTime localDateTime = DataHoraUtils.criar(data, DataHoraUtils.obterTempo("00:00"));

		Assert.assertTrue(localDateTime.getDayOfMonth() == dia);
		Assert.assertTrue(localDateTime.getMonthValue() == mes);
		Assert.assertTrue(localDateTime.getYear() == ano);
		Assert.assertTrue(localDateTime.getHour() == hora);
		Assert.assertTrue(localDateTime.getMinute() == minuto);
	}
}
