package br.laramara.ti.sislaraserver;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class AplicacaoSisLaraServer {
	private static final Logger logger = Logger
			.getLogger(AplicacaoSisLaraServer.class);

	public static void main(String[] args) {
		// Inicializa o Serviço
		Registro.inicializarContexto();
		Registro.inicialiarCaches();
		new Configuracao().atualizarRelatoriosEImagens();
		logger.warn("Serviço do SisLara iniciado.");
		new Configuracao().desabilidarLogNoConsole();
	}
}