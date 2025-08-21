package br.laramara.ti.sislaracommons.utilitarios;

import java.util.regex.Pattern;

public class EmailUtils {
	private static final String PADRAO_VALIDACAO_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean emailValido(String email) {
		return Pattern.compile(PADRAO_VALIDACAO_EMAIL).matcher(email).matches(); 
	}
}
