package br.laramara.sistelemarketingserver.utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MaquinaTempo {
	
	private static LocalDateTime dataAtual;

	private MaquinaTempo() {
	}

	public static LocalDate obterDataAtual() {
		return dataAtual == null ? obterDataEHoraAtual().toLocalDate() : dataAtual.toLocalDate();
	}

	public static LocalDateTime obterDataEHoraAtual() {
		return dataAtual == null ? LocalDateTime.now() : dataAtual;
	}

	public static void mudarDataAtual(String textoData) {
		dataAtual = LocalDateTime.of(DataHoraUtils.obterDataValidaInvalidaOuNulo(textoData), LocalTime.MIN);
	}

	public static void restaurarDataOriginal() {
		dataAtual = null;
	}
}
