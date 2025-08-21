package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoConstrucaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoConstrucao;

public class FabricaTipoConstrucao extends FabricaBase<TipoConstrucaoDTO, TipoConstrucao> {

	public final TipoConstrucaoDTO converterParaDTO(TipoConstrucao tipoConstrucao) {
		return tipoConstrucao != null ? new TipoConstrucaoDTO(tipoConstrucao.getId(), tipoConstrucao.getDescricao())
				: null;
	}

	public final TipoConstrucao converterParaDominio(TipoConstrucaoDTO tipoConstrucaoDto) {
		return tipoConstrucaoDto != null ? new TipoConstrucao(tipoConstrucaoDto.getId(), tipoConstrucaoDto.toString())
				: null;
	}
}
