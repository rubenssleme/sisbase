package br.laramara.ti.sislaraserver.dominio.seguranca;

import br.laramara.ti.sislaraserver.relatorios.Exportador;
import br.laramara.ti.sislaraserver.relatorios.ExportadorPDF;
import br.laramara.ti.sislaraserver.relatorios.ExportadorXLS;

public enum ExtensaoArquivo {
	pdf(new ExportadorPDF()), 
	xls(new ExportadorXLS()),
	jpg(null),
	csv(null), 
	docx(null);

	private final Exportador exportador;

	private ExtensaoArquivo(Exportador exportador) {
		this.exportador = exportador;
	}

	public Exportador obterExportador() {
		return exportador;
	}
}
