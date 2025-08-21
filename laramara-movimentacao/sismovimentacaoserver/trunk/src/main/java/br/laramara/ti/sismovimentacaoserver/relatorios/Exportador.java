package br.laramara.ti.sismovimentacaoserver.relatorios;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

import br.laramara.ti.sismovimentacaoserver.utilitarios.Configuracao;

public abstract class Exportador {

	private final Logger logger;

	public Exportador() {
		this.logger = Logger.getLogger(getClass());
	}

	public abstract byte[] exportar(ModeloRelatorio modeloRelatorio,
			Map<String, Object> argumentos) throws Exception;

	protected Connection obterConexao(ModeloRelatorio modeloDeDados) {
		Connection conexao = null;
		try {
			conexao = modeloDeDados.obterFonteDeDados().getConnection();
		} catch (Exception e) {
			logger.fatal("Erro durante obtenção de conexão do relatório. \nDetalhes: "
					+ e);
		}
		return conexao;
	}

	protected JasperPrint obterJasperPrint(ModeloRelatorio modeloRelatorio,
			Map<String, Object> argumentos) throws Exception {
		Connection conexao = obterConexao(modeloRelatorio);
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				new Configuracao()
						.obterConfiguracao(Configuracao.DIRETORIO_RELATORIOS)
						+ modeloRelatorio.obterNomeArquivo(), argumentos,
				conexao);
		conexao.close();
		return jasperPrint;
	}
}
