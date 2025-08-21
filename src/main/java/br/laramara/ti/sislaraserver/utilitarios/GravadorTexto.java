package br.com.rubensleme.utilitarios;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.com.rubensleme.dominio.usuario.Usuario;

public class GravadorTexto {

	private static final Logger logger = Logger.getLogger(GravadorTexto.class);

	public static final synchronized void gravarHistoricoSeNecessario(boolean gravar,
			Usuario usuario, String textoHistorico, String diretorio) {
		gravar(gravar, usuario, textoHistorico, diretorio);
	}
	
	public static final synchronized void gravarHistoricoSeNecessario(boolean gravar, Usuario usuario,
			List<Map<String, Object>> historicoASalvar, String diretorio) {
		gravar(gravar, usuario, historicoASalvar.toString(), diretorio);
	}
	
	private static final synchronized void gravar(boolean gravar, Usuario usuario, String textoHistorico, String diretorio){
		if (gravar) {
			try {
				FileUtils
						.writeStringToFile(
								new File(
										new Configuracao()
												.obterConfiguracao(diretorio)
												+ usuario.getId().toString()),
								DataHoraUtils
										.obterDataHoraMinutosMilesegundosAtual()
										+ "\n"
										+ textoHistorico
										+ "\n", Charset.defaultCharset(), true);
			} catch (Exception e) {
				logger.fatal("Erro durante gravação de arquivo texto. \nDetalhes: "
						+ e);
			}
		}
	}
}
