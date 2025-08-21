package br.laramara.ti.sislaraserver.repositorios;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sislaraserver.dominio.usuario.ProntuarioEscaneado;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

@Repository
public class RepositorioProntuarioEscaneadoNaRede implements
		RepositorioProntuarioEscaneado {

	protected static final Logger logger = Logger
			.getLogger(RepositorioProntuarioEscaneadoNaRede.class);

	@Override
	public List<ProntuarioEscaneado> obterPorId(Long prontuario) {
		List<ProntuarioEscaneado> prontuariosEscaneados = new ArrayList<>();
		try {
			ExtensaoArquivo extensao = ExtensaoArquivo.jpg;
			File diretorio = new File(obterDiretorio(prontuario));

			for (File arquivo : FileUtils
					.listFiles(
							diretorio,
							new PrefixFileFilter(
									new Configuracao()
											.obterConfiguracao(Configuracao.DIRETORIO_NOME_PADRAO_PRONTUARIOS_ESCANEADOS)
											+ " " + prontuario.toString()),
							null)) {
				prontuariosEscaneados.add(new ProntuarioEscaneado(arquivo
						.getName(), arquivo.lastModified(), new Arquivo(
						new byte[0], extensao)));
			}
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Pontuarios Escaneados. \nDetalhe:"
					+ e);
		}
		Collections.sort(prontuariosEscaneados,
				ProntuarioEscaneado.obterComparador());
		return prontuariosEscaneados;
	}

	private String obterDiretorio(Long prontuario){
		return 
				new Configuracao()
						.obterConfiguracao(Configuracao.DIRETORIO_PRONTUARIOS_ESCANEADOS);
	}
	
	@Override
	public ProntuarioEscaneado obterProntuarioEscaneado(Long prontuario,
			ProntuarioEscaneado prontuarioEscaneado) {
		ProntuarioEscaneado retorno = null;
		try {
			File conteudoArquivo = new File(obterDiretorio(prontuario) + "/"
					+ prontuarioEscaneado.getNomeArquivo());
			retorno = new ProntuarioEscaneado(
					prontuarioEscaneado.getNomeArquivo(), new Arquivo(
							FileUtils.readFileToByteArray(conteudoArquivo),
							ExtensaoArquivo.jpg));
		} catch (Exception e) {
			logger.error("Não foi possível carregar Pontuario Escaneado. \nDetalhe:"
					+ e);
		}
		return retorno;
	}
}
