package br.laramara.ti.sislaraserver.relatorios;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
		if (argumentos == null) {
			argumentos = new HashMap<>();
			argumentos.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				new Configuracao()
						.obterConfiguracao(Configuracao.DIRETORIO_RELATORIOS)
						+ modeloRelatorio.obterNomeArquivo(), argumentos,
				conexao);
		conexao.close();
		return jasperPrint;
	}
}
