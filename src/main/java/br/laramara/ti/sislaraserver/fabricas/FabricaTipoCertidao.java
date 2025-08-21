package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoCertidaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoCertidao;

public class FabricaTipoCertidao extends
		FabricaBase<TipoCertidaoDTO, TipoCertidao> {
	public final TipoCertidaoDTO converterParaDTO(TipoCertidao tipoCertidao) {
		return new TipoCertidaoDTO(tipoCertidao.toString());
	}

	public final TipoCertidao converterParaDominio(TipoCertidaoDTO tipoCertidao) {
		return tipoCertidao != null ? TipoCertidao.valueOf(TipoCertidao.class,
				tipoCertidao.toString()) : null;
	}
}
