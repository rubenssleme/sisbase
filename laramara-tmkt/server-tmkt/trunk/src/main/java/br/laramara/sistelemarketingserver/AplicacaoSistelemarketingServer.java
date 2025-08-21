package br.laramara.sistelemarketingserver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import br.laramara.sistelemarketingserver.utilitarios.Registro;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class AplicacaoSistelemarketingServer implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(AplicacaoSistelemarketingServer.class);

	public static void main(String[] args) throws Exception {
		new SpringApplication(AplicacaoSistelemarketingServer.class).run(args);
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
		logger.warn("Serviço do Sistelemarketing iniciado.");
	}
}