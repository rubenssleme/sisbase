package br.laramara.sistelemarketingserver.utilitarios;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.laramara.sistelemarketingserver.AplicacaoSistelemarketingServer;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuidorContato;
import br.laramara.sistelemarketingserver.dominio.seguranca.Autenticador;
import br.laramara.sistelemarketingserver.dominio.telefonia.SincronizadorTelefonia;

public class Registro {
	
	public static final String NOME_DATA_SOURCE_SISTELEMARKETING = "dataSourceSistelemarketing";
	public static final String NOME_DATA_SOURCE_PBX = "dataSourcePbx";
	
	private static ApplicationContext contexto;
	
	public static void inicializarContexto() {
		if (contexto == null) {
			contexto = (ApplicationContext) new SpringApplication(AplicacaoSistelemarketingServer.class).run();
		}
	}
	
	public static void setContexto(ApplicationContext contexto) {
		Registro.contexto = contexto;
	}

	public static void finalizarContexto() {
		((AbstractApplicationContext) contexto).close();
	}
	
	public static final DataSource obterDataSourceSisTelemarketing() {
		inicializarContexto();
		return (DataSource) (contexto.getBean(NOME_DATA_SOURCE_SISTELEMARKETING));
	}

	public static Autenticador obterAutenticador() {
		inicializarContexto();
		return (Autenticador) (contexto.getBean(Autenticador.class));
	}

	public static DistribuidorContato obterDistribuidorContato() {
		inicializarContexto();
		return (DistribuidorContato) (contexto.getBean(DistribuidorContato.class));
	}

	public static SincronizadorTelefonia obterSincronizadorTelefonia() {
		inicializarContexto();
		return (SincronizadorTelefonia) (contexto.getBean(SincronizadorTelefonia.class));
	}
}
