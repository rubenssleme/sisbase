package br.laramara.ti.sislaraweb.utilitarios;

import java.io.InputStream;
import java.util.Properties;

public class Configuracao {

	private static Properties arquivoPropriedades;

	public static final String ARQUIVO_CONFIGURACAO = "application.properties";

	public static final String ENDERECO_SERVICO_EXTERNO = "endereco.servico.externo";
	
	public static final String PORTA_APLICACAO = "swarm.http.port";
	
	private Configuracao() {
	}

	private synchronized static void inicializar() {
		if (arquivoPropriedades == null)
			try {
				arquivoPropriedades = new Properties();
				InputStream inputStream = Configuracao.class.getClassLoader()
						.getResourceAsStream(ARQUIVO_CONFIGURACAO);
				arquivoPropriedades.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public synchronized static String obterConfiguracao(String chave) {
		inicializar();
		return arquivoPropriedades.getProperty(chave);
	}
	
}
