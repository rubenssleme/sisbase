package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.NomeDocumentoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.NomeDocumento;

public class FabricaNomeDocumento extends FabricaBase<NomeDocumentoDTO, NomeDocumento> {
	public final NomeDocumentoDTO converterParaDTO(NomeDocumento nomeDocumento) {
		return nomeDocumento != null ? new NomeDocumentoDTO(nomeDocumento.toString()) : null;
	}

	public final NomeDocumento converterParaDominio(NomeDocumentoDTO nomeDocumento) {
		return nomeDocumento != null ? NomeDocumento.valueOf(NomeDocumento.class, nomeDocumento.toString()) : null;
	}
}
