package br.laramara.ti.sismovimentacaoserver.utilitarios;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import br.laramara.ti.sismovimentacaocommons.servicos.ServicoSisMovimentacaoServer;
import br.laramara.ti.sismovimentacaoserver.repositorios.QualificadorRepositorioProfissionalSistema;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioContaAcesso;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioMovimentacao;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioPerfil;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioProfissional;

public class Registro {
	
	public static final String NOME_DATA_SOURCE_SISMOVIMENTACAO = "dataSourceSisMovimentacao";
	
	private static ApplicationContext contexto = inicializarContexto();

	public static final RepositorioContaAcesso obterRepositorioContaAcesso() {
		return (RepositorioContaAcesso) (contexto
				.getBean(RepositorioContaAcesso.class));
	}
	
	public static final ServicoSisMovimentacaoServer obterServicoSisMovimentacaoServer() {
		return (ServicoSisMovimentacaoServer) (contexto
				.getBean(ServicoSisMovimentacaoServer.class));
	}
	
	public static RepositorioPerfil obterRepositorioPerfil() {
		return (RepositorioPerfil) (contexto
				.getBean(RepositorioPerfil.class));
	}
	
	public static RepositorioMovimentacao obterRepositorioMovimentacao() {
		return (RepositorioMovimentacao) (contexto
				.getBean(RepositorioMovimentacao.class));
	}

	public static final RepositorioProfissional obterRepositorioProfissional() {
		return (RepositorioProfissional) obterObjetoComAnotacao(QualificadorRepositorioProfissionalSistema.class);
	}
	
	public static final EntityManagerFactory  obterEntityManagerFactory () {
		return (EntityManagerFactory ) (contexto
				.getBean(EntityManagerFactory.class));
	}
	
	public static final DataSource obterDataSourceSisMovimentacao() {
		return (DataSource) (contexto.getBean(NOME_DATA_SOURCE_SISMOVIMENTACAO));
	}
	
	private static final DataSource obterDataSourceSisMovimentacaoLegado() {
		return (DataSource) (contexto.getBean("dataSourceSisMovimentacaoLegado"));
	}
	
	public static final JdbcTemplate obterJdbcTemplateSisMovimentacaoLegado(){
		return new JdbcTemplate(obterDataSourceSisMovimentacaoLegado());
	}
	
	public static final ApplicationContext inicializarContexto() {
		if (contexto == null) {
			contexto = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		}
		return contexto;
	}

	public static void finalizarContexto() {
		((AbstractApplicationContext) contexto).close();
	}

	@SuppressWarnings("rawtypes")
	private static final Object obterObjetoComAnotacao(Class anotacao) {
		Object retorno = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> objetosComAnotacao = contexto
				.getBeansWithAnnotation(anotacao);

		if (objetosComAnotacao.size() == 1) {
			for (Object objetoComAnotacao : objetosComAnotacao.values()) {
				retorno = objetoComAnotacao;
			}
		}
		return retorno;
	}

}
