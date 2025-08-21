package br.laramara.ti.sismovimentacaoserver;

import org.apache.log4j.Logger;

import br.laramara.ti.sismovimentacaoserver.utilitarios.Configuracao;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class AplicacaoSisMovimentacaoServer {
	private static final Logger logger = Logger
			.getLogger(AplicacaoSisMovimentacaoServer.class);

	public static void main(String[] args) {
		// Inicializa o Serviço
		Registro.inicializarContexto();
		new Configuracao().atualizarRelatoriosEImagens();
		logger.warn("Serviço do SisMovimentacao iniciado.");
		new Configuracao().desabilidarLogNoConsole();
	}
}