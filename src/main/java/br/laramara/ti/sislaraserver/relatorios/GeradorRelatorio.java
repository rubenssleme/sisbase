package br.laramara.ti.sislaraserver.relatorios;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Component
public class GeradorRelatorio {
	private static final Logger logger = Logger
			.getLogger(GeradorRelatorio.class);

	public Arquivo gerarRelatorio(ContaAcesso contaAcesso,
			Map<String, Object> parametros, ModeloRelatorio modeloRelatorio) {
		byte[] relatorio = null;
		try {
			Exportador exportador = contaAcesso
					.getExtensaoRelatorios().obterExportador();
			relatorio = exportador.exportar(modeloRelatorio,
					parametros);
		} catch (Exception e) {
			logger.fatal("Erro durante geração de relatório. "
					+ "OBS: A conexão do relatório não foi fechada. Ficar atendo ao limite de conexções abertas. \n Detalhes: "
					+ e);
		}
		return new Arquivo(relatorio, contaAcesso.getExtensaoRelatorios());
	}
}
