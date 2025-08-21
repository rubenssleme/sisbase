package br.laramara.ti.sislaracommons.utilitarios;

import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

import com.rits.cloning.Cloner;

public class DataHoraUtils {

	private static final Logger logger = Logger.getLogger(DataHoraUtils.class);

	private static int LIMITE_HORA_DIA = 13;
	private static int LIMITE_MINUTO_DIA = 59;
	private static String segundos = ":00";
	private static String segundosMili = segundos + ".000";
	private static String formatacaoDataCompacta = "dd/MM/yy";
	private static String formatacaoData = formatacaoDataCompacta + "yy";
	private static String formatacaoDataHora = formatacaoData + " HH:mm:ss";
	private static String formatacaoDataHoraMinuto = formatacaoData + " HH:mm";
	private static String formatacaoDataHoraMinutoSegundoMile = "yyyyMMdd_HHmmssSSS";

	public static String obterDataHoraMinutosMilesegundosAtual() {
		return formatarDataHoraMinutosMile(MaquinaTempo.obterInstancia().obterCalendarioAtual());
	}
	
	public static Calendar obterDataAposDias(Calendar data, Integer dias) {
		Calendar novaData = Calendar.getInstance();
		novaData.setTime(data.getTime());
		novaData.add(Calendar.DATE, dias);
		return novaData;
	}

	public static String formatarDataHoraMinutosMile(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(
				formatacaoDataHoraMinutoSegundoMile).print(
				new DateTime(data.getTime())) : "";
	}

	public static String formatarData(LocalDate data) {
		return (data != null) ? data.format(DateTimeFormatter.ofPattern(formatacaoData)) : "";
	}

	public static String formatarData(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoData)
				.print(new DateTime(data.getTime())) : "";
	}
	
	public static String formatarDataCompacta(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoDataCompacta)
				.print(new DateTime(data.getTime())) : "";
	}

	public static String formatarData(Date data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoData)
				.print(new DateTime(data.getTime())) : "";
	}

	public static String formatarDataHora(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoDataHora)
				.print(new DateTime(data.getTime())) : "";
	}

	public static Calendar obterDataInvalida() {
		return criar(31, 12, 9999);
	}

	public static Calendar criar(int dia, int mes, int ano) {
		return new GregorianCalendar(ano, mes - 1, dia);
	}

	public static Calendar criar(int dia, int mes, int ano, int hora,
			int minuto, int segundo) {
		return new GregorianCalendar(ano, mes - 1, dia, hora, minuto, segundo);
	}

	public static LocalDateTime criar(Calendar data, Time horario) {
		return LocalDateTime.of(data.get(Calendar.YEAR), data.get(Calendar.MONTH) + 1, data.get(Calendar.DAY_OF_MONTH),
				horario.toLocalTime().getHour(), horario.toLocalTime().getMinute());
	}

	private static String[] quebrarData(String data) {
		return data.split("/");
	}

	private static String[] quebrarHora(String hora) {
		return hora.split(":");
	}

	public static Calendar criar(String data) {
		Calendar resultado = null;

		if (dataValida(data)) {
			String[] dataSeparada = quebrarData(data);
			resultado = criar(Integer.parseInt(dataSeparada[0]),
					Integer.parseInt(dataSeparada[1]),
					Integer.parseInt(dataSeparada[2]));
		} else if (dataHoraValida(data)) {
			String apenasData = data.substring(0, 10);
			String apenasHora = data.substring(11, data.length());
			String[] dataSeparada = quebrarData(apenasData);
			String[] horaSeparada = quebrarHora(apenasHora);
			resultado = criar(Integer.parseInt(dataSeparada[0]),
					Integer.parseInt(dataSeparada[1]),
					Integer.parseInt(dataSeparada[2]),
					Integer.parseInt(horaSeparada[0]),
					Integer.parseInt(horaSeparada[1]),
					Integer.parseInt(horaSeparada[2]));
		}
		return resultado;
	}

	public static boolean dataValida(String s) {
		return validar(s, obterFormatoData(), 10);
	}

	private static boolean dataHoraValida(String s) {
		return validar(s, obterFormatoDataHora(), 18);
	}

	private static boolean horaMinutoValida(String s) {
		return validar("31/12/2001 " + s, obterFormatoDataHoraMinuto(), 15);
	}

	private static boolean validar(String s, SimpleDateFormat simpleDateFormat,
			int tamanhoTexto) {
		boolean formatoCerto = simpleDateFormat.parse(s, new ParsePosition(0)) != null;
		boolean tamanhoExato = s.replace(" ", "").length() == tamanhoTexto;
		if (tamanhoExato == true && formatoCerto == true) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean dataPreenchida(String s) {
		return s != null ? s.replace("/", "").replace(" ", "").length() > 0
				: false;
	}

	public static boolean dataInvalida(Calendar data) {
		return (data != null && data.equals(obterDataInvalida()));
	}

	private static boolean horaInvalida(String hora) {
		if (hora == null) {
			return true;
		} else if (!horaMinutoValida(hora)) {
			return true;
		}

		return false;
	}

	public static boolean horaInvalida(Time hora) {
		return horaInvalida(DataHoraUtils.obterHora(hora));
	}

	public static boolean horaTerminoAnteriorHoraInicio(Time horaInicio,
			Time horaTermino) {
		boolean retorno = false;
		if (!horaInvalida(horaInicio) && !horaInvalida(horaTermino)) {
			retorno = horaTermino.before(horaInicio);
		}
		return retorno;
	}

	public static Calendar obterDataValidaInvalidaOuNulo(String data) {
		Calendar retorno = null;
		if (dataPreenchida(data)) {
			if (dataValida(data) || dataHoraValida(data)) {
				retorno = criar(data);
			} else {
				retorno = obterDataInvalida();
			}
		}
		return retorno;
	}

	public static Date obterDataOuNulo(String data) {
		Calendar dataCalendario = obterDataValidaInvalidaOuNulo(data);
		if ((dataCalendario != null && !dataCalendario
				.equals(obterDataInvalida()))) {
			return dataCalendario.getTime();
		} else {
			return null;
		}
	}

	private static SimpleDateFormat obterFormatoData() {
		SimpleDateFormat formatoData = new SimpleDateFormat(formatacaoData);
		formatoData.setLenient(false);
		return formatoData;
	}

	private static SimpleDateFormat obterFormatoDataHora() {
		SimpleDateFormat formatoData = new SimpleDateFormat(formatacaoDataHora);
		formatoData.setLenient(false);
		return formatoData;
	}

	private static SimpleDateFormat obterFormatoDataHoraMinuto() {
		SimpleDateFormat formatoData = new SimpleDateFormat(
				formatacaoDataHoraMinuto);
		formatoData.setLenient(false);
		return formatoData;
	}

	public static String obterHora(String horas) {
		return (String) horas.subSequence(0, horas.length() - 7);
	}

	public static String obterHora(Time hora) {
		String retorno = null;
		if (hora != null) {
			retorno = hora.toString().substring(0, 5);
		}
		return retorno;
	}

	public static String retornaTotalHoraOuInvalido(String hora) {
		String horaSemEspaco = TextoUtils.removerCaracteresInvalidos(hora);
		if (horaSemEspaco.length() != 6) {
			return null;
		} else {
			return horaSemEspaco + segundosMili;
		}
	}

	public static Time obterTempo(String horaMinuto) {
		Time retorno = null;
		try {
			if (!horaInvalida(horaMinuto)) {
				retorno = Time.valueOf(horaMinuto + segundos);
			}
		} catch (IllegalArgumentException e) {
			loggarErroParse(e);
		}
		return retorno;
	}

	private static long obterMillisegundos(Time tempoInicio, Time tempoTermino) {
		return (long) (tempoTermino.getTime() - tempoInicio.getTime());
	}

	public static Time obterTempoDecorrido(Time horaInicio, Time horaTermino) {
		Time tempoDecorrido = null;
		if (!horaInvalida((horaInicio)) && !horaInvalida((horaTermino))) {
			try {
				long milisegundoDecorridos = obterMillisegundos(horaInicio,
						horaTermino);

				SimpleDateFormat formatMilli = new SimpleDateFormat("S");
				tempoDecorrido = new Time(formatMilli.parse(
						String.valueOf(milisegundoDecorridos)).getTime());
			} catch (ParseException e) {
				loggarErroParse(e);
			}
		}
		return tempoDecorrido;
	}

	private static void loggarErroParse(Exception e) {
		logger.fatal("Erro durante obtenção de tempo decorrido. Detalhes: +"
				+ e.getMessage());
	}

	public static boolean dataAnteriorDataAtual(Calendar data) {
		Calendar dataAtualSistema =  MaquinaTempo.obterInstancia().obterCalendarioAtual();
		dataAtualSistema.set(Calendar.HOUR_OF_DAY, 0);
		dataAtualSistema.set(Calendar.MINUTE, 0);
		dataAtualSistema.set(Calendar.SECOND, 0);
		dataAtualSistema.set(Calendar.MILLISECOND, 0);
		return data.before(dataAtualSistema);
	}
	
	public static boolean dataPosteriorDataAtual(Calendar data) {
		Calendar dataAtualSistema = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		dataAtualSistema.set(Calendar.HOUR_OF_DAY, 23);
		dataAtualSistema.set(Calendar.MINUTE, 59);
		dataAtualSistema.set(Calendar.SECOND, 59);
		dataAtualSistema.set(Calendar.MILLISECOND, 000);
		return data.after(dataAtualSistema);
	}

	public static boolean dataTerminoAnteriorDataInicio(Calendar dataInicio,
			Calendar dataTermino) {
		boolean retorno = false;
		if (dataInicio != null && dataTermino != null
				&& !dataInvalida(dataInicio) && !dataInvalida(dataTermino)) {
			retorno = dataTermino.before(dataInicio);
		}
		return retorno;
	}
		
	public static boolean dataIgualOuPosterior(Calendar dataA, Calendar dataB) {
		if (dataA.equals(dataB))
			return true;
		return (dataA.after(dataB));
	}

	public static boolean dataIgualOuAnterior(Calendar dataA, Calendar dataB) {
		if (dataA.equals(dataB))
			return true;
		return (dataA.before(dataB));
	}
	
	public static int obterAnosTranscorridos(Calendar data) {
		return Years
				.yearsBetween(new DateTime(data.getTimeInMillis()),
						new DateTime(MaquinaTempo.obterInstancia().obterCalendarioAtual().getTimeInMillis()))
				.getYears();
	}
	
	public static int obterAnosTranscorridos(Calendar dataInicial, Calendar dataFinal) {
		return Years.yearsBetween(new DateTime(dataInicial.getTimeInMillis()), new DateTime(dataFinal)).getYears();
	}

	public static int obterMesesTranscorridos(Calendar data) {
		return Months
				.monthsBetween(new DateTime(data.getTimeInMillis()),
						new DateTime(MaquinaTempo.obterInstancia().obterCalendarioAtual().getTimeInMillis()))
				.getMonths();
	}

	public static boolean aposMeioPeriodo(Time hora) {
		LocalTime horaInicioLocal = LocalTime.of(hora.toLocalTime().getHour(), hora.toLocalTime().getMinute());
		return horaInicioLocal.isAfter(LocalTime.of(LIMITE_HORA_DIA, LIMITE_MINUTO_DIA));
	}

	public static boolean dataIgual(Calendar dataInicio, Calendar dataTermino) {
		return dataInicio.equals(dataTermino);
	}

	public static boolean expirouLimite24Horas(Calendar data, Time hora) {
		Calendar dataLimite24Horas = new Cloner().deepClone(data);
		dataLimite24Horas.set(Calendar.HOUR, hora.toLocalTime().getHour());
		dataLimite24Horas.set(Calendar.MINUTE, hora.toLocalTime().getMinute());
		dataLimite24Horas.add(Calendar.HOUR, 24);
		return MaquinaTempo.obterInstancia().obterCalendarioAtual().after(dataLimite24Horas);
	}
	
	private static Calendar avancarOuRecuarAnos(Calendar data, int anos) {
		return avancarOuRecuar(data, Calendar.YEAR, anos);
	}
	
	private static Calendar avancarOuRecuarMeses(Calendar data, int meses){
		return avancarOuRecuar(data, Calendar.MONTH, meses);
	}
	
	private static Calendar avancarOuRecuar(Calendar data, int tipo, int quantidade) {
		Calendar dataRetorno = new Cloner().deepClone(data);
		dataRetorno.add(tipo, quantidade);
		return dataRetorno;
	}
	
	public static Calendar recuar6Meses(Calendar data){
		return avancarOuRecuarMeses(data, -6);
	}
	
	public static Calendar avancarMeses(Calendar data, int meses){
		return avancarOuRecuarMeses(data, meses);
	}
	
	public static Calendar avancarDoisAnos(Calendar data){
		return avancarOuRecuarAnos(data, 2);
	}
}
