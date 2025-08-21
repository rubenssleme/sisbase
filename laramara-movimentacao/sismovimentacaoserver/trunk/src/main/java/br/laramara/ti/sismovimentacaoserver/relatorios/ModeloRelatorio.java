package br.laramara.ti.sismovimentacaoserver.relatorios;

import javax.sql.DataSource;

import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public enum ModeloRelatorio {
	MODELO_BASE("MODELO_BASE", Registro.obterDataSourceSisMovimentacao());

	private final String nomeArquivo;
	private final DataSource fonteDeDados;

	private ModeloRelatorio(String nomeArquivo, DataSource fonteDeDados) {
		this.nomeArquivo = nomeArquivo;
		this.fonteDeDados = fonteDeDados;
	}

	public String obterNomeArquivo() {
		return nomeArquivo;
	}

	public DataSource obterFonteDeDados() {
		return fonteDeDados;
	}
}
