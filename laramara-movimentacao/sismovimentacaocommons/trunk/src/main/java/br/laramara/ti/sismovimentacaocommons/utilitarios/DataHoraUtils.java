package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DataHoraUtils {

	private static final Logger logger = Logger.getLogger(DataHoraUtils.class);

	private static String segundos = ":00";
	private static String segundosMili = segundos + ".000";
	private static String formatacaoData = "dd/MM/yyyy";
	private static String formatacaoDataHora = formatacaoData + " HH:mm:ss";
	private static String formatacaoDataHoraMinuto = formatacaoData+" HH:mm";
	private static String formatacaoDataHoraMinutoSegundoMile = "yyyyMMdd_HHmmssSSS";

	public static String obterDataAtual(){
		return formatarDataHoraMinutosMile(Calendar.getInstance());
	}
	
	public static String formatarDataHoraMinutosMile(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoDataHoraMinutoSegundoMile)
				.print(new DateTime(data.getTime())) : "";
	}
	
	public static String formatarData(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoData)
				.print(new DateTime(data.getTime())) : "";
	}

	public static String formatarData(Date data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoData)
				.print(new DateTime(data.getTime())) : "";
	}

	public static String formatarDataHoraMinuto(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoDataHoraMinuto)
				.print(new DateTime(data.getTime())) : "";
	}
	
	public static String formatarDataHoraMinutoSegundo(Calendar data) {
		return (data != null) ? DateTimeFormat.forPattern(formatacaoDataHora)
				.print(new DateTime(data.getTime())) : "";
	}

	public static Calendar obterDadaInvalida() {
		return criar(31, 12, 9999);
	}

	public static Calendar criar(int dia, int mes, int ano) {
		return new GregorianCalendar(ano, mes - 1, dia);
	}

	public static Calendar criar(int dia, int mes, int ano, int hora,
			int minuto) {
		return new GregorianCalendar(ano, mes - 1, dia, hora, minuto);
	}
	
	public static Calendar criar(int dia, int mes, int ano, int hora,
			int minuto, int segundo) {
		return new GregorianCalendar(ano, mes - 1, dia, hora, minuto, segundo);
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
		} else if(dataHoraMinutoValida(data)){
			String apenasData = data.substring(0, 10);
			String apenasHora = data.substring(11, data.length());
			String[] dataSeparada = quebrarData(apenasData);
			String[] horaSeparada = quebrarHora(apenasHora);
			resultado = criar(Integer.parseInt(dataSeparada[0]),
					Integer.parseInt(dataSeparada[1]),
					Integer.parseInt(dataSeparada[2]),
					Integer.parseInt(horaSeparada[0]),
					Integer.parseInt(horaSeparada[1]));
		}else if (dataHoraMinutoSegundoValida(data)) {
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

	private static boolean dataHoraMinutoValida(String s) {
		return validar(s, obterFormatoDataHoraMinuto(), 15);
	}
	
	private static boolean dataHoraMinutoSegundoValida(String s) {
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
		return (data != null && data.equals(obterDadaInvalida()));
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
			if (dataValida(data) || dataHoraMinutoValida(data) || dataHoraMinutoSegundoValida(data)) {
				retorno = criar(data);
			} else {
				retorno = obterDadaInvalida();
			}
		}
		return retorno;
	}

	public static Date obterDataOuNulo(String data) {
		Calendar dataCalendario = obterDataValidaInvalidaOuNulo(data);
		if ((dataCalendario != null && !dataCalendario
				.equals(obterDadaInvalida()))) {
			return dataCalendario.getTime();
		} else {
			return null;
		}
	}
	
	public static boolean dataInvalida(String data){
		return obterDataOuNulo(data) == null;
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
		SimpleDateFormat formatoData = new SimpleDateFormat(formatacaoDataHoraMinuto);
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

	public static Time obterTempo(String hora) {
		Time retorno = null;
		try {
			if (!horaInvalida(hora)) {
				retorno = Time.valueOf(hora + segundos);
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
		Calendar dataAtualSistema = Calendar.getInstance();
		dataAtualSistema.set(Calendar.HOUR_OF_DAY, 0);
		dataAtualSistema.set(Calendar.MINUTE, 0);
		dataAtualSistema.set(Calendar.SECOND, 0);
		dataAtualSistema.set(Calendar.MILLISECOND, 0);
		return data.before(dataAtualSistema);
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
}
