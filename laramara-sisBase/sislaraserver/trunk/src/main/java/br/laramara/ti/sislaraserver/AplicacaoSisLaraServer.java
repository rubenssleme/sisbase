package br.laramara.ti.sislaraserver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class AplicacaoSisLaraServer implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(AplicacaoSisLaraServer.class);

	public static void main(String[] args) throws Exception {
		new SpringApplication(AplicacaoSisLaraServer.class).run(args);
	}

	@Inject
	ApplicationContext contexto;

	@PostConstruct
	public void inicializar() {
		Registro.setContexto(contexto);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Inicializa o Serviço
		Registro.inicializarContexto();
		Registro.inicialiarCaches();
		new Configuracao().atualizarRelatoriosEImagens();
		logger.warn("Serviço do SisLara iniciado.");
	}
}