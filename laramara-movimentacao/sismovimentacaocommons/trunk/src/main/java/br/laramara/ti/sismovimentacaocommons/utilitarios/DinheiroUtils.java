package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DinheiroUtils {
	public static final BigDecimal converterParaDinheiro(String valor) {
		return new BigDecimal(valor.replace(".", "").replace(",", "."));
	}

	public static final boolean dinheiroPreenchida(String valor){
		return valor != null && valor.length() > 0;
	}
	
	public static final boolean validaParaDinheiro(String valor) {
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
		String valorAnteriorDecimal = valor.substring(0, valor.length() - 3);
		String valorPosteriorDecimal = valor.substring(valor.length() - 2,
				valor.length());
		Matcher matherAnterirorDecimal = p.matcher(valorAnteriorDecimal);
		Matcher matherPosteriorDecimal = p.matcher(valorPosteriorDecimal);

		boolean retorno = matherAnterirorDecimal.find()
				|| matherPosteriorDecimal.find();
		return !retorno;
	}
	
	public static final String converterDinheiroParaString(BigDecimal valor){
		return valor.toString().replace(".", ",");
	}

	public static BigDecimal obterDinheiroInvalido() {
		return new BigDecimal("9999999999999999.99");
	}
}
