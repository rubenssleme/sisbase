package br.laramara.ti.sislaraserver.utilitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class Configuracao {

	private static final Logger logger = Logger.getLogger(Configuracao.class);

	private static PropertyResourceBundle arquivoPropriedades;
	
	public static final String DIRETORIO_RELATORIOS = "diretorio.relatorios";
	public static final String DIRETORIO_TELAS_CAPTURADAS = "diretorio.telas_capturadas";
	public static final String DIRETORIO_FOTOS = "diretorio.fotos";
	public static final String DIRETORIO_ARQUIVOS_DEMANDA = "diretorio.arquivos.demanda";
	public static final String DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL = "diretorio.arquivos.atendimento_individual";
	public static final String DIRETORIO_ARQUIVOS_ATENDIMENTO_GRUPO = "diretorio.arquivos.atendimento_grupo";
	public static final String DIRETORIO_ARQUIVOS_ATENDIMENTO_USUARIO = "diretorio.arquivos.atendimento_usuario";
	public static final String DIRETORIO_ARQUIVOS_PROJETO = "diretorio.arquivos.projeto";
	public static final String DIRETORIO_PRONTUARIOS_ESCANEADOS = "diretorio.prontuarios.escaneados";
	public static final String DIRETORIO_PRONTUARIOS_ESPERA_EXCESSO_FALTAS = "diretorio.prontuarios.espera.excesso.faltas";
	public static final String DIRETORIO_PRONTUARIOS_ATUALIZACAO_STATUS = "diretorio.prontuarios.atualizacao.status";
	public static final String DIRETORIO_NOME_PADRAO_PRONTUARIOS_ESCANEADOS = "diretorio.nome.padrao.prontuarios.escaneados";
	public static final String EXTENSAO_FOTOS = "extensao.fotos";
	public static final String ARQUIVO_SESSAO = "arquivo.sessao";
	public static final String ARQUIVO_COORDENACAO_USUARIO = "arquivo.coordenacao.usuario";
	public static final String ARQUIVO_COORDENACAO_GRUPO = "arquivo.coordenacao.grupo";
	public static final String ARQUIVO_COORDENACAO_GERAL = "arquivo.coordenacao.geral";
	public static final String SENHA_PADRAO_CONTA_ACESSO = "senha.padrao.conta.acesso";
	public static final String DIRETORIO_ARQUIVOS_COBRANCAS = "diretorio.arquivos.cobrancas";
	public static final String EMAIL_HOST = "email.host";
	public static final String EMAIL_PORTA = "email.porta";
	public static final String EMAIL_ENVIO = "email.envio";
	public static final String EMAIL_SENHA = "email.senha";
	public static final String EMAIL_DESTINO_COBRANCA = "email.destino.cobranca";
	public static final String EMAIL_MIME_HTML = "email.mime.html";
	public static final String EMAIL_IP_LINK = "email.ip.link";
	
	public static final String SERVIDOR_ENDERECO_IP = "server.address";
	public static final String SERVIDOR_PORTA = "server.port";

	public static final String APLICACAO_WEB_PORTA = "aplicacao.web.porta";
	
	public static final String ARQUIVO_CONFIGURACAO = "/application.properties";

	private void inicializar() {
		if (arquivoPropriedades == null)
			try {
				arquivoPropriedades = new PropertyResourceBundle(
						getClass().getResourceAsStream(ARQUIVO_CONFIGURACAO));
			} catch (Exception e) {
				logger.fatal("Erro durante carregamento de arquivo de configuração.\n Detalhes: "
						+ e);
			}
	}

	public final String obterConfiguracao(String chave) {
		inicializar();
		return arquivoPropriedades.getString(chave);
	}
	
	public void atualizarRelatoriosEImagens() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			String diretorioDestino = obterConfiguracao(Configuracao.DIRETORIO_RELATORIOS);
		
			extrair(diretorioDestino, resolver.getResources("classpath:relatorios/*.jasper"));
			extrair(diretorioDestino, resolver.getResources("classpath:relatorios/*.gif"));
			extrair(diretorioDestino, resolver.getResources("classpath:relatorios/*.png"));
		} catch (FileNotFoundException c){
			logger.warn("Arquivo Jar da aplicação não foi localizado.");
		} catch (Exception e) {
			logger.error("Erro durante extração de arquivos de relatório. \nDetalhes: "
					+ e);
		}
	}

	private void extrair(String diretorioDestino, Resource[] recursos) throws IOException {
		for(Resource resource : recursos){
			String nomeArquivo = resource.getFilename();
			File arquivo = new File(diretorioDestino + nomeArquivo);
			FileUtils.copyInputStreamToFile(resource.getInputStream(), arquivo);
			logger.info("Arquivo " + nomeArquivo + " extraido com sucesso no " + arquivo);
		}
	}
}
