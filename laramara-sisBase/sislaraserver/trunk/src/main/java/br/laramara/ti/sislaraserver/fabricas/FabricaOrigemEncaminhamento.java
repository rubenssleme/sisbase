package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;

public class FabricaOrigemEncaminhamento extends FabricaBase<OrigemEncaminhamentoDTO, OrigemEncaminhamento> {

	public final OrigemEncaminhamentoDTO converterParaDTO(OrigemEncaminhamento origemEncaminhamento) {
		return origemEncaminhamento != null
				? new OrigemEncaminhamentoDTO(origemEncaminhamento.getId(), origemEncaminhamento.getDescricao()) : null;
	}

	public final OrigemEncaminhamento converterParaDominio(OrigemEncaminhamentoDTO origemEncaminhamentoDto) {
		return origemEncaminhamentoDto != null
				? new OrigemEncaminhamento(origemEncaminhamentoDto.getId(), origemEncaminhamentoDto.toString()) : null;
	}
}
