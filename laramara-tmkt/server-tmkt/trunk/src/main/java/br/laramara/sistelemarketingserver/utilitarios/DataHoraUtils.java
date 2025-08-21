package br.laramara.sistelemarketingserver.utilitarios;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DataHoraUtils {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String HH_MM_SSS = "HH:mm:ss.SSS";
	private static final String FORMATO_DATA_HORA = DD_MM_YYYY +" "+ HH_MM_SSS;
		
	private static final DateTimeFormatter padraoData = DateTimeFormatter.ofPattern(DD_MM_YYYY);
	private static final DateTimeFormatter padraoDataHora = DateTimeFormatter.ofPattern(FORMATO_DATA_HORA);

	private static final LocalDateTime dataHoraInvalida = LocalDateTime.MAX;

	public static LocalDate obterDataValidaInvalidaOuNulo(String data) {
		if (data != null && !data.isEmpty()) {
			try {
				return LocalDate.parse(data, padraoData);
			} catch (DateTimeParseException e) {
				return dataHoraInvalida.toLocalDate();
			}
		}
		return null;
	}
	
	public static boolean dataInvalida(LocalDate data) {
		return data != null && data.equals(dataHoraInvalida.toLocalDate());
	}

	public static String formatarData(LocalDate dataInicio) {
		return dataInicio != null ? dataInicio.format(padraoData) : "";
	}
	
	public static String formatarDataHora(LocalDateTime dataHora) {
		return dataHora != null ? dataHora.format(padraoDataHora) : "";
	}
	
	public static String formatarDataHora(Date dataHora) {
		return dataHora != null ? new SimpleDateFormat(FORMATO_DATA_HORA).format(dataHora) : "";
	}

	public static boolean datasForaDeOrdem(LocalDate dataInicio, LocalDate dataTermino) {
		if (dataInicio != null && dataTermino != null && !dataInvalida(dataInicio) && !dataInvalida(dataTermino)
				&& dataInicio.isAfter(dataTermino)) {
			return true;
		}
		return false;
	}

	public static boolean estaNoPeriodo(LocalDate dataInicio, LocalDate dataTermino) {
		LocalDate dataAtual = MaquinaTempo.obterDataAtual();
		return (dataInicio != null && (dataAtual.equals(dataInicio) || dataAtual.isAfter(dataInicio)))
				&& (dataTermino == null
						|| (dataTermino != null && (dataAtual.equals(dataTermino) || dataAtual.isBefore(dataTermino))));
	}

	public static Date converterParaDate(LocalDateTime dataEfetuacao) {
		return Date.from(dataEfetuacao
				.atZone(ZoneId.of(new Configuracao().obterConfiguracao(Configuracao.SPRING_JACKSON_TIMEZONE)))
				.toInstant());
	}

	public static LocalDateTime converterParaLocalDateTime(Date dataHora) {
		return dataHora.toInstant()
				.atZone(ZoneId.of(new Configuracao().obterConfiguracao(Configuracao.SPRING_JACKSON_TIMEZONE)))
				.toLocalDateTime();
	}
}
