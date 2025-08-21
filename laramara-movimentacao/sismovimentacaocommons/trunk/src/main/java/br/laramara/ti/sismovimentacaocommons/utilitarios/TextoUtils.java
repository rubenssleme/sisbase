package br.laramara.ti.sismovimentacaocommons.utilitarios;


public class TextoUtils {
	public static String anularVazio(String texto) {
		if (texto == null || texto.isEmpty()) {
			return null;
		} else {
			return texto;
		}
	}

	public static boolean estaVazio(String texto) {
		return (removerCaracteresInvalidos(texto).isEmpty());
	}

	public static String removerCaracteresInvalidosRG(
			String rg) {
		String textoSemFormatacao = removerCaracteresInvalidosEAnularVazio(rg);
		return (textoSemFormatacao != null) ? textoSemFormatacao.replace(".",
				"").replace("-", "") : "";
	}
	
	public static String removerCaracteresInvalidosEAnularVazio(String texto) {
		if (texto != null) {
			String retorno = removerCaracteresInvalidos(texto);
			return TextoUtils.anularVazio(retorno);
		} else {
			return null;
		}
	}
	
	public static String removerCaracteresInvalidos(String texto) {
		return texto.replace(" ", "").replace("(", "").replace(")", "");
	}
	
    public static String removerChaves(String texto){
        return texto != null ? texto.replace("[", "").replace("]", "") : null;
    }

	public static String removerVirgulaFinal(String texto) {
		String textoSemEspacoInicialFinal = texto.trim();
		return textoSemEspacoInicialFinal.substring(0, textoSemEspacoInicialFinal.length() - 1);
	}
}
