package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;

public class ContextoArquivo {

	public static List<ArquivoDTO> obterArquivoDtoDocumentoAVFUN() {
		return Arrays.asList(new ArquivoDTO(null, "AVFUN.docx", "CONTEUDO".getBytes(), null));
	}
}
