package br.laramara.ti.sismovimentacaoserver.dominio.seguranca;

import br.laramara.ti.sismovimentacaoserver.relatorios.Exportador;
import br.laramara.ti.sismovimentacaoserver.relatorios.ExportadorPDF;
import br.laramara.ti.sismovimentacaoserver.relatorios.ExportadorXLS;

public enum ExtensaoArquivo {
	pdf(new ExportadorPDF()), 
	xls(new ExportadorXLS()),
	jpg(null);

	private final Exportador exportador;

	private ExtensaoArquivo(Exportador exportador) {
		this.exportador = exportador;
	}

	public Exportador obterExportador() {
		return exportador;
	}
}
