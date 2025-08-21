package br.laramara.sistelemarketingserver;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.utilitarios.Registro;

public class TestesIntegracaoAbstrato {
	private static final Logger logger = Logger.getLogger(TestesIntegracaoAbstrato.class);

	private Connection conexao;
	private File arquivoParaInsersaoDeDados;
	private IDatabaseConnection conexaoDbUnit;
	private IDataSet dataSet;
	private TestRestTemplate restTemplate;

	private void alterarSequencia(String sequencia, Long valor) throws SQLException {
		obterConexaoSisTelemarketing().createStatement()
				.execute("ALTER SEQUENCE " + sequencia + " RESTART WITH " + valor.toString());
	}
	
	protected TestesIntegracaoAbstrato(String nomeDoArquivoParaInsercao) {
		if (!nomeDoArquivoParaInsercao.equals("")) {
			try {
				arquivoParaInsersaoDeDados = new File("src/test/resources/" + nomeDoArquivoParaInsercao);
			} catch (Exception e) {
				logger.fatal("Erro durante o preparo dos arquivos de dados para teste.", e);
			}
		}
	}

	@BeforeMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	protected void efetuarInsercaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.INSERT.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
			alterarSequencia("nivel_id_seq", new Long(3));
		} catch (Exception e) {
			logger.fatal("Erro durante a inserção de dados de teste.", e);
		}
	}

	@AfterMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	protected void efetuarRemocaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.DELETE_ALL.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
		} catch (Exception e) {
			logger.fatal("Erro durante a remoção de dados de teste.", e);
		}
	}

	protected <T> T executarGet(String url, Class<T> resultadoClasse) {
		T resultado = null;

		try {
			resultado = getRestTemplate().getForEntity(url, resultadoClasse).getBody();
		} catch (Exception e) {
			logger.fatal("Falha ao obter resultado da solicitação GET.\nDetalhes: " + e.getMessage());
		}
		return resultado;
	}
	
	protected <T> T executarPost(String url, Object objeto, Class<T> resultadoClasse) {
		T resultado = null;
		
		try {
			resultado = getRestTemplate().postForEntity(url, criarEntidadeJson(objeto), resultadoClasse).getBody();
		} catch (Exception e) {
			logger.fatal("Falha ao obter resultado da solicitação POST.\nDetalhes: " + e.getMessage());
		}
		return resultado;
	}

	protected <T> HttpEntity<T> criarEntidadeJson(T objeto) {
		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new HttpEntity<>(objeto, requestHeaders);
	}

	private TestRestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new TestRestTemplate();
		}

		return restTemplate;
	}

	private IDatabaseConnection obterConexaoDBUnit() {
		if (conexaoDbUnit == null) {
			try {
				conexaoDbUnit = new DatabaseConnection(obterConexaoSisTelemarketing());
				DatabaseConfig configuracao = conexaoDbUnit.getConfig();
				configuracao.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
			} catch (Exception e) {
				logger.fatal("Erro durante a configuração do DBUnit", e);
			}
		}
		return conexaoDbUnit;
	}

	private Connection obterConexaoSisTelemarketing() {
		if (conexao == null) {
			conexao = DataSourceUtils.getConnection(Registro.obterDataSourceSisTelemarketing());
		}
		return conexao;
	}

	private IDataSet obterDataSetDBUnit() {
		if (dataSet == null) {
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			try {
				dataSet = builder.build(arquivoParaInsersaoDeDados);
			} catch (Exception e) {
				logger.fatal("Erro durante a criação do arquivo de dados de testes do DBUnit.");
			}
		}
		return dataSet;
	}
}
