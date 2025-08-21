package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroUtils {

	private static Integer obterNumeroInteiroInvalido() {
		return new Integer(999999999);
	}
	
	private static Long obterNumeroLongoInvalido() {
		return new Long(999999999);
	}
	
	public static Integer retornaInteiroOuInvalido(String texto) {
		Integer retorno = new Integer(0);
		if (texto != null && !TextoUtils.estaVazio(texto)) {
			Pattern padraoApenasNumeros = Pattern.compile("[0-9]*");
			if (((Matcher) padraoApenasNumeros.matcher(TextoUtils
					.removerCaracteresInvalidos(texto))).matches()) {
				retorno = new Integer(
						TextoUtils.removerCaracteresInvalidos(texto));
			} else {
				retorno = obterNumeroInteiroInvalido();
			}
		} else {
			retorno = null;
		}
		return retorno;
	}
	
	public static Long retornaLongoOuInvalido(String texto) {
		Long retorno = new Long(0);
		if (texto != null && !TextoUtils.estaVazio(texto)) {
			Pattern padraoApenasNumeros = Pattern.compile("[0-9]*");
			if (((Matcher) padraoApenasNumeros.matcher(TextoUtils
					.removerCaracteresInvalidos(texto))).matches()) {
				retorno = new Long(TextoUtils.removerCaracteresInvalidos(texto));
			} else {
				retorno = obterNumeroLongoInvalido();
			}
		} else {
			retorno = null;
		}
		return retorno;
	}

	public static boolean numeroInteiroInvalido(Integer numero) {
		return (numero != null && numero.equals(obterNumeroInteiroInvalido()));
	}
	
	public static boolean numeroLongoInvalido(Long numero) {
		return (numero != null && numero.equals(obterNumeroLongoInvalido()));
	}
	
	public static String obterTexto(Integer numero){
		return numero != null ? numero.toString() : "";
	}
}
