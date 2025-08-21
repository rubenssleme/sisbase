package br.laramara.sistelemarketingserver.utilitarios;

import java.util.PropertyResourceBundle;

import org.apache.log4j.Logger;

public class Configuracao {

	private static final Logger logger = Logger.getLogger(Configuracao.class);

	private static PropertyResourceBundle arquivoPropriedades;
	
	public static final String SERVIDOR_ENDERECO = "server.address";
	public static final String SERVIDOR_PORTA = "server.port";
	public static final String PBX_API_ENDERECO = "pbx.api.endereco";
	public static final String PBX_API_PORTA = "pbx.api.porta";
	public static final String PBX_API_LOGIN = "pbx.api.login";
	public static final String PBX_API_SENHA = "pbx.api.senha";
	public static final String SPRING_JACKSON_TIMEZONE = "spring.jackson.time-zone";
		
	public static final String ARQUIVO_CONFIGURACAO = "/application.properties";

	private void inicializar() {
		if (arquivoPropriedades == null)
			try {
				arquivoPropriedades = new PropertyResourceBundle(
						getClass().getResourceAsStream(ARQUIVO_CONFIGURACAO));
			} catch (Exception e) {
				logger.fatal("Erro durante carregamento de arquivo de configuração.\n Detalhes: "
						+ e);
			}
	}

	public final String obterConfiguracao(String chave) {
		inicializar();
		return arquivoPropriedades.getString(chave);
	}
}
