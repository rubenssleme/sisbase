package br.laramara.ti.sislaracommons.utilitarios;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DinheiroUtils {
	
	public static final BigDecimal obterDinheiroOuInvalido(String valor) {
		if (valor != null && valor.isEmpty()) {
			return null;
		}
		if (dinheiroPreenchida(valor) && validaParaDinheiro(valor)) {
			return converterParaDinheiro(valor);
		} else {
			return obterDinheiroInvalido();
		}
	}
	
	private static final BigDecimal converterParaDinheiro(String valor) {
		return new BigDecimal(valor.replace(".", "").replace(",", "."));
	}

	private static final boolean dinheiroPreenchida(String valor){
		return valor != null && valor.length() > 0;
	}
	
	private static final boolean validaParaDinheiro(String valor) {
		return verificaExitenciaDeVirgula(valor) && verificaExistenciaSomenteNumeros(valor);
	}
	
	private static final boolean verificaExitenciaDeVirgula(String valor) {
		boolean retorno;
		try {
			String virgula = valor.substring(valor.length() - 3,
					valor.length() - 2);
			retorno = virgula.equals(",");
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	private static final boolean verificaExistenciaSomenteNumeros(
			String valor) {
		Pattern p = Pattern.compile("[^0-9]");
		String valorAnteriorDecimal = valor.substring(0, valor.length() - 3).replace(".", "");
		String valorPosteriorDecimal = valor.substring(valor.length() - 2,
				valor.length());
		Matcher matherAnterirorDecimal = p.matcher(valorAnteriorDecimal);
		Matcher matherPosteriorDecimal = p.matcher(valorPosteriorDecimal);

		boolean retorno = matherAnterirorDecimal.find()
				|| matherPosteriorDecimal.find();
		return !retorno;
	}

	public static BigDecimal obterDinheiroInvalido() {
		return new BigDecimal("9999999999999999.99");
	}
	
	public static boolean eDinheiroValido(BigDecimal valor){
		return valor.equals(obterDinheiroInvalido());
	}

	public static String obterDinheiro(BigDecimal valor) {
		return valor != null ? valor.toString().replace(".", ",") : "";
	}
	
	public static boolean primeiroValorMaiorOuIgualQueSegundoValor(BigDecimal valorA, BigDecimal valorB) {
		return valorA != null && valorB != null && valorA.compareTo(valorB) == 0 || primeiroValorMaiorQueSegundoValor(valorA, valorB);
	}
	
	public static boolean valorNegativo(BigDecimal valorA) {
		return valorA != null && valorA.compareTo(BigDecimal.ZERO) == -1;
	}
	
	public static boolean primeiroValorMaiorQueSegundoValor(BigDecimal valorA,
			BigDecimal valorB) {
		return valorA != null && valorB != null && valorA.compareTo(valorB) == 1;
	}
}
