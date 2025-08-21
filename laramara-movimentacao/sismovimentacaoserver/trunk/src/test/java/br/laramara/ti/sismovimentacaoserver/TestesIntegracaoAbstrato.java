package br.laramara.ti.sismovimentacaoserver;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Cache;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public abstract class TestesIntegracaoAbstrato {
	private static final Logger logger = Logger
			.getLogger(TestesIntegracaoAbstrato.class);

	private File arquivoParaInsersaoDeDados;
	private IDatabaseConnection conexaoDbUnit;
	
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
		}
	}

	@BeforeMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	protected void efetuarInsercaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.INSERT.execute(obterConexaoDBUnit(),
					obterDataSetDBUnit());
		} catch (Exception e) {
			logger.fatal("Erro durante a inserção de dados de teste.", e);
		}
	}

	@AfterMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	protected void efetuarRemocaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.DELETE_ALL.execute(obterConexaoDBUnit(),
					obterDataSetDBUnit());
			limparCache();
		} catch (Exception e) {
			logger.fatal("Erro durante a remoção de dados de teste.", e);
		}
	}
	
	private void limparCache() {
		Cache cache = Registro.obterEntityManagerFactory().getCache();
		cache.evictAll();
	}

	private IDatabaseConnection obterConexaoDBUnit() {
		if (conexaoDbUnit == null) {
			try {
				Connection conexao = DataSourceUtils.getConnection(Registro
						.obterDataSourceSisMovimentacao());
				conexaoDbUnit = new DatabaseConnection(conexao);
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

	private ReplacementDataSet obterDataSetDBUnit() {
		ReplacementDataSet dataSet = null;
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		try {
			dataSet = new ReplacementDataSet(
					builder.build(arquivoParaInsersaoDeDados));
			dataSet.addReplacementObject("$[data_atual]", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		} catch (Exception e) {
			logger.fatal("Erro durante a criação do arquivo de dados de testes do DBUnit.");
		}
		return dataSet;
	}
}
