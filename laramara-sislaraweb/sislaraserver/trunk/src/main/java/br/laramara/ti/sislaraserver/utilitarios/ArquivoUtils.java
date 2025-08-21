package br.laramara.ti.sislaraserver.utilitarios;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import br.laramara.ti.sislaraserver.dominio.Arquivo;

public class ArquivoUtils {
	
	public static String extensao;
	
	private static String obterExtensao() {
		if (extensao == null) {
			extensao = new Configuracao()
					.obterConfiguracao(Configuracao.EXTENSAO_FOTOS);
		}
		return extensao;
	}

	public static boolean validarDuplicacaoArquivos(List<Arquivo> arquivos) {
		List<String> nomesArquivos = arquivos.stream().map(arquivo -> arquivo.getNome()).collect(Collectors.toList());
		return EntidadeUtils.possuiOcorrenciaDuplicada(nomesArquivos);
	}
	
	public static File obterArquivoFoto(Long prontuario) {
		return new File(
				new Configuracao()
						.obterConfiguracao(Configuracao.DIRETORIO_FOTOS)
						+ prontuario.toString() + "." + obterExtensao());
	}
}
