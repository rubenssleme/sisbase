package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaraserver.dominio.Arquivo;

public class ContextoArquivo {

	public static List<ArquivoDTO> obterArquivoDtoDocumentoAVFUN() {
		return Arrays.asList(new ArquivoDTO(null, "AVFUN.docx", "CONTEUDO".getBytes(), null));
	}

	public static List<Arquivo> obterArquivo() {
		return Arrays.asList(gerarArquivo());
	}
	

	public static List<Arquivo> obterArquivosDuplicados() {
		return Arrays.asList(gerarArquivo(), gerarArquivo());
	}

	private static Arquivo gerarArquivo() {
		return new Arquivo(null, "AVFUN.docx", "CONTEUDO".getBytes());
	}
}
