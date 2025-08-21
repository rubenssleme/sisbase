package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Criptografia {

	private static final Logger logger = Logger.getLogger(Criptografia.class);

	public static String converterParaMD5(String texto) {
		String retorno = null;
		if (texto != null && !texto.isEmpty()) {
			MessageDigest digest = null;
			try {
				digest = java.security.MessageDigest.getInstance("MD5");
				digest.update(texto.getBytes());
				retorno = converterParaBytes(digest.digest());
			} catch (Exception e) {
				logger.fatal("Erro durante geração do MD5. \nDetalhes: "
						+ e.getMessage());
			}
		}
		return retorno;
	}

	private static String converterParaBytes(byte[] b) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		String hex = "";
		int msb;
		int lsb = 0;
		int i;

		for (i = 0; i < b.length; i++) {
			msb = ((int) b[i] & 0x000000FF) / 16;
			lsb = ((int) b[i] & 0x000000FF) % 16;
			hex = hex + hexChars[msb] + hexChars[lsb];
		}
		return hex;
	}
}
