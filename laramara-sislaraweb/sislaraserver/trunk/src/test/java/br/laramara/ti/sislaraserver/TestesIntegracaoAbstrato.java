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
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import br.laramara.ti.sislaracommons.servicos.rest.ContratoRest;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public abstract class TestesIntegracaoAbstrato {
	private static final Logger logger = Logger.getLogger(TestesIntegracaoAbstrato.class);

	private Connection conexao;
	private File arquivoParaInsersaoDeDados;
	private IDatabaseConnection conexaoDbUnit;
	private IDataSet dataSet;
	private TestRestTemplate restTemplate;

	protected TestesIntegracaoAbstrato(String nomeDoArquivoParaInsercao) {
		if (!nomeDoArquivoParaInsercao.equals("")) {
			try {
				arquivoParaInsersaoDeDados = new File("src/test/resources/" + nomeDoArquivoParaInsercao);
			} catch (Exception e) {
				logger.fatal("Erro durante o preparo dos arquivos de dados para teste.", e);
			}
		}
	}

	@BeforeMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB, TiposDeTeste.ATENDIMENTO_INDIVIDUAL,
			TiposDeTeste.AGENDAMENTO, TiposDeTeste.ESPERA, TiposDeTeste.GRUPO, TiposDeTeste.INSTITUICAO,
			TiposDeTeste.USUARIO, TiposDeTeste.CONTRIBUINTE, TiposDeTeste.PRE_CADASTRO, TiposDeTeste.PENDENCIA,
			TiposDeTeste.SOLICITACAO_RELATORIO, TiposDeTeste.DEMANDA, TiposDeTeste.RECIBO, TiposDeTeste.PROJETO })
	protected void efetuarInsercaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.INSERT.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
			alterarSequencia("conta_acesso_id_seq", new Long(1));
			alterarSequencia("atendimento_grupo_id_seq", new Long(20000));
		} catch (Exception e) {
			logger.fatal("Erro durante a inserção de dados de teste.", e);
		}
	}

	private void alterarSequencia(String sequencia, Long valor) throws SQLException {
		obterConexaoSislara().createStatement()
				.execute("ALTER SEQUENCE " + sequencia + " RESTART WITH " + valor.toString());
	}

	@AfterMethod(groups = { TiposDeTeste.INTEGRACAO_COM_DB, TiposDeTeste.ATENDIMENTO_INDIVIDUAL,
			TiposDeTeste.AGENDAMENTO, TiposDeTeste.ESPERA, TiposDeTeste.GRUPO, TiposDeTeste.INSTITUICAO,
			TiposDeTeste.USUARIO, TiposDeTeste.CONTRIBUINTE, TiposDeTeste.PRE_CADASTRO, TiposDeTeste.PENDENCIA,
			TiposDeTeste.SOLICITACAO_RELATORIO, TiposDeTeste.DEMANDA, TiposDeTeste.RECIBO, TiposDeTeste.PROJETO })
	protected void efetuarRemocaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.DELETE_ALL.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
			limparCache();
			limparDiretorios(new String[] { Configuracao.DIRETORIO_ARQUIVOS_COBRANCAS,
					Configuracao.DIRETORIO_PRONTUARIOS_ATUALIZACAO_STATUS,
					Configuracao.DIRETORIO_PRONTUARIOS_ESPERA_EXCESSO_FALTAS, Configuracao.DIRETORIO_FOTOS,
					Configuracao.DIRETORIO_TELAS_CAPTURADAS, Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_USUARIO,
					Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL,
					Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_GRUPO,
					Configuracao.DIRETORIO_ARQUIVOS_PROJETO});
			desbloquearTodos();
		} catch (Exception e) {
			logger.fatal("Erro durante a remoção de dados de teste.", e);
		}
	}

	private void desbloquearTodos() {
		Registro.obterCoordenadorEdicaoGeral().desbloquearTodos();
	}

	protected <T> T executarGet(String url, Class<T> resultadoClasse) {
		T resultado = null;

		try {
			resultado = getRestTemplate().getForEntity(url, resultadoClasse).getBody();
		} catch (Exception e) {
			logger.fatal("Falha ao obter resultado da solicitação POST.\nDetalhes: " + e.getMessage());
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

	private void limparCache() {
		Cache cache = Registro.obterEntityManagerFactory().getCache();
		cache.evictAll();

		Registro.obterCacheGrupo().limpar();
		Registro.obterCacheEspera().limpar();
		Registro.obterCacheInstituicao().limpar();
	}

	private void limparDiretorios(String[] diretorios) throws IOException {
		for (String diretorio : diretorios) {
			FileUtils.deleteQuietly(new File(new Configuracao().obterConfiguracao(diretorio)));
		}
	}

	private IDatabaseConnection obterConexaoDBUnit() {
		if (conexaoDbUnit == null) {
			try {
				conexaoDbUnit = new DatabaseConnection(obterConexaoSislara());
				DatabaseConfig configuracao = conexaoDbUnit.getConfig();
				configuracao.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
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
	
	private static String obterUrlBase(String url) {
		return "http://"
				+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_ENDERECO_IP) + ":"
				+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_PORTA) + url;
	}

	protected String obterUrlUsuarioExternoAutenticacao() {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_AUTENTICACAO);
	}
	
	protected String obterUrlUsuarioExternoCadastro() {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_CADASTRO);
	}
	
	protected String obterUrlInscricaoCadastro() {
		return obterUrlBase(ContratoRest.URL_INSCRICAO_CADASTRO);
	}
	
	protected String obterUrlUsuarioExternoConsultaPerfilPreenchido() {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_PERFIL_PREENCHIDO);
	}
	
	protected String obterUrlUsuarioExternoSolicitacaoRecuperacaoSenha() {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_SOLICITACAO_RECUPERACAO_SENHA);
	}
	
	protected static String obterUrlMunicipioListagemPorUf(String uf) {
		return obterUrlBase(ContratoRest.URL_MUNICIPIO_LISTAGEM_POR_UF).replace(ContratoRest.URL_PARAMETRO_UF, uf);
	}
	
	protected static String obterUrlEnderecoConsultaPorCep(String cep) {
		return obterUrlBase(ContratoRest.URL_ENDERECO_CONSULTA_POR_CEP).replace(ContratoRest.URL_PARAMETRO_CEP, cep);
	}
	
	protected static String obterUrlUsuarioExternoConsultaPorToken(String token) {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_POR_TOKEN).replace(ContratoRest.URL_PARAMETRO_TOKEN, token);
	}
	
	protected static String obterUrlDetalheCursoConsultaPorIdGrupo(Long idGrupo) {
		return obterUrlBase(ContratoRest.URL_DETALHE_CURSO_CONSULTA_POR_IDGRUPO).replace(ContratoRest.URL_PARAMETRO_IDGRUPO, idGrupo.toString());
	}
	
	protected String obterUrlDetalheCursoListagem() {
		return obterUrlBase(ContratoRest.URL_DETALHE_CURSO_LISTAGEM);
	}

	protected String obterUrlUsuarioExternoEdicao() {
		return obterUrlBase(ContratoRest.URL_USUARIO_EXTERNO_EDICAO);
	}
	
	protected String obterUrlUfListagem() {
		return obterUrlBase(ContratoRest.URL_UF_LISTAGEM);
	}
}
