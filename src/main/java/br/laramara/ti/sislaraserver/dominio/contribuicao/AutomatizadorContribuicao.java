package br.laramara.ti.sislaraserver.dominio.contribuicao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContribuinte;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.EmailUtils;

@Component
public class AutomatizadorContribuicao {

	private final Logger logger = Logger.getLogger(AutomatizadorContribuicao.class);

	@Inject
	private RepositorioContribuinte repositorioContribuinte;
	@Inject
	private RepositorioSislara repositorioSislara;

	private static final String AGENCIA = "1976";
	private static final String CONTA_CORRENTE = "01800";
	private static final String DAC_CONTA_CORRENTE = "0";
	private static final String NOME_EMPRESA = "LARAMARA ASS.BRAS.ASSIST.AO DEFICIENTE VISUAL";
	private static final String CODIGO_BANCO = "341";
	private static final String NOME_BANCO = "BANCO ITAU SA";
	private static final String DATA_GERACAO = "030815";
	private static final String NUMERO_CARTEIRA = "173";
	private static final String CODIGO_MOEDA = "0";
	private static final String LITERAL_MOEDA = "REAL";
	private static final String PREFIXO_ARQUIVO_COBRANCA = "CB";
	private static final String EXTENSAO_ZIP = ".zip";

	private static final String DIRETORIO_ARQUIVOS_COBRANCAS = new Configuracao()
			.obterConfiguracao(Configuracao.DIRETORIO_ARQUIVOS_COBRANCAS);

	public boolean gerarArquivoEEnviarParaEmail() {
		try {
			EmailUtils.enviarArquivoPorEmail("Arquivo de remessa de contribuições do Sislara. ",
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_HOST),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_PORTA),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_ENVIO),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_SENHA),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_DESTINO_COBRANCA),
					gerarArquivosCobranca(MaquinaTempo.obterInstancia().obterCalendarioAtual()));
			return true;
		} catch (Exception e) {
			logger.fatal("Erro durante geração de arquivo de cobrança. Detalhes: " + e);
			return false;
		}
	}

	public File gerarArquivosCobranca(Calendar data) throws Exception {
		String dataGeracao = DataHoraUtils.formatarDataCompacta(data);
		String nomeArquivo = PREFIXO_ARQUIVO_COBRANCA + dataGeracao.replace("/", "");
		List<Contribuinte> contribuintesAtivos = repositorioContribuinte.obterTodos().stream()
				.filter(contribuinte -> contribuinte.estaAtivado()).collect(Collectors.toList());
		ArquivoCobranca arquivoCombranca = new ArquivoCobranca(AGENCIA, CONTA_CORRENTE,
				DAC_CONTA_CORRENTE, NOME_EMPRESA, CODIGO_BANCO, NOME_BANCO, DATA_GERACAO, repositorioSislara,
				contribuintesAtivos, NUMERO_CARTEIRA, CODIGO_MOEDA, LITERAL_MOEDA, dataGeracao);
		return gravarArquivoCobrancaERetornarZipado(nomeArquivo, arquivoCombranca);
	}

	private File gravarArquivoCobrancaERetornarZipado(String nomeArquivo, ArquivoCobranca arquivoCobranca)
			throws Exception {
		File arquivoConbranca = new File(DIRETORIO_ARQUIVOS_COBRANCAS + nomeArquivo);
		FileUtils.writeStringToFile(arquivoConbranca, arquivoCobranca.obterTexto(), Charset.defaultCharset());
		return ziparArquivo(arquivoConbranca);
	}

	private File ziparArquivo(File arquivoConbranca) throws Exception {
		byte[] buffer = new byte[1024];
		String nomeArquivoZipado = arquivoConbranca.getAbsolutePath() + EXTENSAO_ZIP;
		FileOutputStream fos = new FileOutputStream(nomeArquivoZipado);
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry ze = new ZipEntry(arquivoConbranca.getName());
		zos.putNextEntry(ze);
		FileInputStream in = new FileInputStream(arquivoConbranca);
		int len;
		while ((len = in.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}
		in.close();
		zos.closeEntry();
		zos.close();
		return new File(nomeArquivoZipado);
	}
}
