package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;

public class FabricaTipoVinculo extends
		FabricaBase<TipoVinculoDTO, TipoVinculo> {

	public final TipoVinculoDTO converterParaDTO(TipoVinculo tipoVinculo) {
		return tipoVinculo != null ? new TipoVinculoDTO(tipoVinculo.getId(),
				tipoVinculo.getDescricao()) : null;
	}

	public final TipoVinculo converterParaDominio(TipoVinculoDTO tipoVinculoDto) {
		return tipoVinculoDto != null ? new TipoVinculo(tipoVinculoDto.getId(),
				tipoVinculoDto.toString()) : null;
	}
}
