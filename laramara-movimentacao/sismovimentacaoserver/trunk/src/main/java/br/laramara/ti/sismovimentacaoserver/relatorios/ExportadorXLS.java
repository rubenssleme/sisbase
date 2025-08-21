package br.laramara.ti.sismovimentacaoserver.relatorios;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import br.laramara.ti.sismovimentacaoserver.utilitarios.Configuracao;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class ExportadorXLS extends Exportador {

	@Override
	public byte[] exportar(ModeloRelatorio modeloRelatorio,
			Map<String, Object> argumentos) throws Exception {
		String nomeArquivo = obterNomeArquivo();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
				obterJasperPrint(modeloRelatorio, argumentos));
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				nomeArquivo);
		exporterXLS.exportReport();
		return obterDadosELimpar(nomeArquivo);
	}

	private String obterNomeArquivo() {
		return new Configuracao()
				.obterConfiguracao(Configuracao.DIRETORIO_RELATORIOS)
				+ UUID.randomUUID().toString();
	}

	private byte[] obterDadosELimpar(String nomeArquivo) throws Exception {
		byte[] retorno = FileUtils.readFileToByteArray(new File(nomeArquivo));
		FileUtils.deleteQuietly(new File(nomeArquivo));

		return retorno;
	}
}
