package br.laramara.ti.sislaraserver.relatorios;

import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;

public class ExportadorPDF extends Exportador {

	@Override
	public byte[] exportar(ModeloRelatorio modeloRelatorio,
			Map<String, Object> argumentos) throws Exception {
		return JasperExportManager.exportReportToPdf(obterJasperPrint(
				modeloRelatorio, argumentos));
	}
}
