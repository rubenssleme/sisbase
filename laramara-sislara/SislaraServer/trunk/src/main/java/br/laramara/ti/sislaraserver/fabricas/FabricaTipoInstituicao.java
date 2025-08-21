package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaraserver.dominio.instituicao.TipoInstituicao;

public class FabricaTipoInstituicao extends
		FabricaBase<TipoInstituicaoDTO, TipoInstituicao> {
	public final TipoInstituicaoDTO converterParaDTO(
			TipoInstituicao tipo) {
		return tipo != null ? new TipoInstituicaoDTO(tipo.toString()) : null;
	}

	public final TipoInstituicao converterParaDominio(
			TipoInstituicaoDTO tipo) {
		return tipo != null ? TipoInstituicao.valueOf(TipoInstituicao.class,
				tipo.toString()) : null;
	}
}
