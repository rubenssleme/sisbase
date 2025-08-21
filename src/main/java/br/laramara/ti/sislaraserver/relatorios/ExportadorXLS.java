package br.laramara.ti.sislaraserver.relatorios;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ExportadorXLS extends Exportador {

	@Override
	public byte[] exportar(ModeloRelatorio modeloRelatorio,
			Map<String, Object> argumentos) throws Exception {
		String nomeArquivo = obterNomeArquivo();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setExporterInput(new SimpleExporterInput(obterJasperPrint(modeloRelatorio, argumentos)));
		exporterXLS.setConfiguration(new SimpleXlsReportConfiguration());
		exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(nomeArquivo));
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
