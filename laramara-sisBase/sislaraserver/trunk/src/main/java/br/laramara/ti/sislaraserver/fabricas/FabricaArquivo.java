package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaraserver.dominio.Arquivo;

public class FabricaArquivo extends FabricaBase<ArquivoDTO, Arquivo> {

	public final ArquivoDTO converterParaDTO(Arquivo arquivo) {
		return arquivo != null ? new ArquivoDTO(arquivo.getId(), arquivo.getNome(), arquivo.getConteudo(),
				arquivo.getExtensao() != null ? arquivo.getExtensao().toString() : null) : null;
	}

	public final Arquivo converterParaDominio(ArquivoDTO arquivoDto) {
		return arquivoDto != null
				? new Arquivo(arquivoDto.getId(), arquivoDto.getNome(), arquivoDto.obterConteudo()) : null;
	}
}
