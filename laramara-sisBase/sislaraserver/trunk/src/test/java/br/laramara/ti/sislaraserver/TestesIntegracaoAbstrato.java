package br.laramara.ti.sislaraserver;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.Cache;

import org.apache.commons.io.FileUtils;
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
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public abstract class TestesIntegracaoAbstrato {
	private static final Logger logger = Logger
			.getLogger(TestesIntegracaoAbstrato.class);

	private Connection conexao;
	private File arquivoParaInsersaoDeDados;
	private IDatabaseConnection conexaoDbUnit;
	private IDataSet dataSet;
	private TestRestTemplate restTemplate;
	
	protected TestesIntegracaoAbstrato(String nomeDoArquivoParaInsercao) {
		if (!nomeDoArquivoParaInsercao.equals("")) {
			try {
				arquivoParaInsersaoDeDados = new File("src/test/resources/"
						+ nomeDoArquivoParaInsercao);
			} catch (Exception e) {
				logger.fatal(
						"Erro durante o preparo dos arquivos de dados para teste.",
						e);
			}
			restTemplate = new TestRestTemplate();
		}
	}
	
	@BeforeMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB, TiposDeTeste.ATENDIMENTO_INDIVIDUAL,
			TiposDeTeste.AGENDAMENTO, TiposDeTeste.ESPERA, TiposDeTeste.GRUPO, TiposDeTeste.INSTITUICAO,
			TiposDeTeste.USUARIO, TiposDeTeste.CONTRIBUINTE, TiposDeTeste.PRE_CADASTRO, TiposDeTeste.PENDENCIA,
			TiposDeTeste.SOLICITACAO_RELATORIO, TiposDeTeste.DEMANDA, TiposDeTeste.RECIBO, TiposDeTeste.PROJETO })
	protected void efetuarInsercaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.INSERT.execute(obterConexaoDBUnit(),
					obterDataSetDBUnit());
			alterarSequencia("conta_acesso_id_seq", new Long(1));
			alterarSequencia("atendimento_grupo_id_seq", new Long(20000));
		} catch (Exception e) {
			logger.fatal("Erro durante a inserção de dados de teste.", e);
		}
	}

	private void alterarSequencia(String sequencia, Long valor) throws SQLException{
		obterConexaoSislara().createStatement().execute("ALTER SEQUENCE " + sequencia + " RESTART WITH " + valor.toString());
	}
	
	@AfterMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB, TiposDeTeste.ATENDIMENTO_INDIVIDUAL,
			TiposDeTeste.AGENDAMENTO, TiposDeTeste.ESPERA, TiposDeTeste.GRUPO, TiposDeTeste.INSTITUICAO,
			TiposDeTeste.USUARIO, TiposDeTeste.CONTRIBUINTE, TiposDeTeste.PRE_CADASTRO, TiposDeTeste.PENDENCIA,
			TiposDeTeste.SOLICITACAO_RELATORIO, TiposDeTeste.DEMANDA, TiposDeTeste.RECIBO, TiposDeTeste.PROJETO })
	protected void efetuarRemocaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.DELETE_ALL.execute(obterConexaoDBUnit(),
					obterDataSetDBUnit());
			limparCache();
			limparDiretorios(new String[] { Configuracao.DIRETORIO_ARQUIVOS_COBRANCAS,
					Configuracao.DIRETORIO_PRONTUARIOS_ATUALIZACAO_STATUS,
					Configuracao.DIRETORIO_PRONTUARIOS_ESPERA_EXCESSO_FALTAS, Configuracao.DIRETORIO_FOTOS,
					Configuracao.DIRETORIO_TELAS_CAPTURADAS, Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_USUARIO,
					Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL,
					Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_GRUPO });
			desbloquearTodos();
		} catch (Exception e) {
			logger.fatal("Erro durante a remoção de dados de teste.", e);
		}
	}
	
	private void desbloquearTodos() {
		Registro.obterCoordenadorEdicaoGeral().desbloquearTodos();
	}

	protected <T> ResponseEntity<T> executarPost(String url, Object objeto, Class<T> resultado) {
		return getRestTemplate().postForEntity(url, objeto, resultado);
	}
	
	protected <T> HttpEntity<T> criarEntidadeJson(T objeto) {
		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new HttpEntity<>(objeto, requestHeaders);
	}
	
	private TestRestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	private void limparCache() {
		Cache cache = Registro.obterEntityManagerFactory().getCache();
		cache.evictAll();
		
		Registro.obterCacheGrupo().limpar();
		Registro.obterCacheEspera().limpar();
		Registro.obterCacheInstituicao().limpar();
	}

	private void limparDiretorios(String[] diretorios) throws IOException {
		for(String diretorio : diretorios){
			FileUtils.deleteDirectory(
					new File(new Configuracao().obterConfiguracao(diretorio)));
			
		}
	}
	
	private IDatabaseConnection obterConexaoDBUnit() {
		if (conexaoDbUnit == null) {
			try {
				conexaoDbUnit = new DatabaseConnection(obterConexaoSislara());
				DatabaseConfig configuracao = conexaoDbUnit.getConfig();
				configuracao.setProperty(
						DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
						new PostgresqlDataTypeFactory());
			} catch (Exception e) {
				logger.fatal("Erro durante a configuração do DBUnit", e);
			}
		}
		return conexaoDbUnit;
	}

	private Connection obterConexaoSislara() {
		if (conexao == null) {
			conexao = DataSourceUtils.getConnection(Registro.obterDataSourceSisLara());
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
